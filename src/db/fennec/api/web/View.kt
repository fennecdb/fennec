package db.fennec.api.web

import io.vertx.ext.web.RoutingContext

interface View {
    fun render(routingContext: RoutingContext): String
}