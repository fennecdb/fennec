package db.fennec.api.web.grafana

import com.google.gson.JsonArray
import db.fennec.api.web.View
import db.fennec.driver.FennecDriver
import io.vertx.ext.web.RoutingContext
import com.google.common.cache.CacheLoader
import java.util.concurrent.TimeUnit
import com.google.common.cache.CacheBuilder
import com.google.common.cache.LoadingCache
import com.google.common.collect.ImmutableSet
import db.fennec.api.web.FennecRestServer.Companion.respondJson
import java.lang.Exception


class GrfSearchView(val driver: FennecDriver) : View {

    var namespaces: LoadingCache<String, Iterable<String>> = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(object : CacheLoader<String, Iterable<String>>() {
                @Throws(Exception::class)
                override fun load(key: String): Iterable<String> {
                    return driver.listNS()
                }
            })

    // delivers back the options for the drop down selection
    override fun render(routingContext: RoutingContext): String {
        var list: Iterable<String> = listOf()
        routingContext.request().bodyHandler {
            val body = it.toJsonObject()
            if (body.containsKey(TARGET_KEY)) {
                val target = it.toJsonObject().getString(TARGET_KEY)
                val searchTarget = SearchTarget.findByAPIValue(target)
                val result = searchTarget?.handler?.invoke(this, target)
                if (result != null) {
                    list = result
                }
            }

            val jsonArray = JsonArray()
            for (el in list) {
                jsonArray.add(el)
            }
            respondJson(routingContext, jsonArray.toString())
        }
        return ""
    }

    companion object {
        private val TARGET_KEY = "target"

        fun handleNamespace(view: GrfSearchView, target: String): Iterable<String> {
            return view.namespaces.get("")
        }

        fun handleField(view: GrfSearchView, target: String): Iterable<String> {
            val firstColon = target.indexOf(':')
            if (firstColon > 0) {
                val ns = target.substring(firstColon + 1)
                return view.driver.listFields(ns)
            }
            return listOf()
        }

        fun handleTransformation(view: GrfSearchView, target: String): Iterable<String> {
            return listOf("none", "m1", "m5", "m60")
        }

        enum class SearchTarget(val apiValue: String, val handler: (GrfSearchView, String) -> Iterable<String> = { _, _ -> listOf() }) {
            NS("ns", GrfSearchView.Companion::handleNamespace),
            FIELD("field", GrfSearchView.Companion::handleField),
            TRANSFORMATION("transform", GrfSearchView.Companion::handleTransformation),
            ALIAS("alias")
            ;

            companion object {
                fun findByAPIValue(apiValue: String): SearchTarget? {
                    for (target in SearchTarget.values()) {
                        if (apiValue.startsWith(target.apiValue)) {
                            return target
                        }
                    }
                    return null
                }
            }
        }
    }
}