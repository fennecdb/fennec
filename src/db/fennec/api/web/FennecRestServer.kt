package db.fennec.api.web

import com.google.common.flogger.FluentLogger
import db.fennec.common.LogDefinition.Companion.config
import db.fennec.api.web.grafana.*
import db.fennec.api.web.rest.DashboardView
import db.fennec.api.web.rest.RootView
import db.fennec.core.GlobalConstants
import db.fennec.driver.FennecDriver
import db.fennec.driver.FennecRawDriver
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.apache.commons.io.FileUtils
import java.io.File

class FennecRestServer(val driver: FennecDriver, val restPort: Int = GlobalConstants.DEFAULT_REST_PORT) : AbstractVerticle() {

    private var innerThread: Thread? = null

    fun launchAsync() {
        innerThread = Thread {
            launch()
        }
        innerThread?.start()
    }

    fun halt() {
        innerThread?.stop()
    }

    fun launch() {
        log.atWarning().log("Starting resting server under :$restPort,\nDashboard available " +
                "under http://localhost:$restPort/dashboard")
        val vxOptions = VertxOptions().setBlockedThreadCheckInterval(200000000)
        vertx = Vertx.vertx(vxOptions)
        val router = Router.router(vertx)

        val rootView = RootView()
        router.route().path("/").handler { routingContext ->
            routingContext.respondHtml(rootView.render(routingContext))
        }

        router.route().path("/style/main.css").handler { routingContext ->
            routingContext.response()
                    .putHeader("content-type", "text/css")
                    .end(FileUtils.readFileToString(File("resc/web/main.css")))
        }

        val dashView = DashboardView(driver)
        router.route().path("/dashboard").handler { routingContext ->
            routingContext.respondHtml(FileUtils.readFileToString(File("./dashboard/index.html")))
        }
        router.route().path("/dashboard/bundle.js").handler { routingContext ->
            routingContext.respondJs(FileUtils.readFileToString(File("./dashboard/build/bundle/bundle.js")))
        }

        // Grafana SimpleJson Endpoints
        val grfRootView = GrfRootView()
        router.route().path("/grafana/").handler { routingContext ->
            log.atInfo().log("[grf] /")
            routingContext.respondHtml(grfRootView.render(routingContext))
        }
        val grfSearchView = GrfSearchView(driver)
        router.route().path("/grafana/search").handler { routingContext ->
            log.atInfo().log("[grf] /search")
            grfSearchView.render(routingContext)
//            routingContext.respondJson(grfSearchView.render(routingContext))
        }
        val grfQueryView = GrfQueryView(driver)
        router.route().path("/grafana/query").handler { routingContext ->
            log.atInfo().log("[grf] /query")
            grfQueryView.render(routingContext)
//            respondJson(routingContext, grfQueryView.render(routingContext))
        }
        val grfAnnotationView = GrfAnnotationView()
        router.route().path("/grafana/annotations").handler { routingContext ->
            log.atInfo().log("[grf] /annotations")
            respondJson(routingContext, grfAnnotationView.render(routingContext))
        }
        val grfTagsKeysView = GrfTagsKeysView()
        router.route().path("/grafana/tag-keys").handler { routingContext ->
            log.atInfo().log("[grf] /tag-keys")
            respondJson(routingContext, grfTagsKeysView.render(routingContext))
        }
        val grfTagValueView = GrfTagsValuesView()
        router.route().path("/grafana/tag-values").handler { routingContext ->
            log.atInfo().log("[grf] /tag-values")
            respondJson(routingContext, grfTagValueView.render(routingContext))
        }

        vertx.createHttpServer().requestHandler(router).listen(restPort)
    }

    fun RoutingContext.respondHtml(html: String) {
        response().putHeader("content-type", "text/html").end(html)
    }

    fun RoutingContext.respondJs(html: String) {
        response().putHeader("content-type", "text/javascript").end(html)
    }

    companion object {
        private val log = FluentLogger.forEnclosingClass().config()

        fun respondJson(routingContext: RoutingContext, json: String) {
            routingContext.response()
                    .putHeader("Access-Control-Allow-Headers", "accept, content-type")
                    .putHeader("Access-Control-Allow-Methods", "POST")
                    .putHeader("Access-Control-Allow-Origin", "*")
                    .putHeader("Content-Type", "application/json")
                    .end(json)
        }
    }
}

fun main(args: Array<String>) {
    FennecRestServer(FennecRawDriver(true)).launch()
}
