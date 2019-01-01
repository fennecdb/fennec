package db.fennec.api.web.grafana

import db.fennec.api.web.View
import io.vertx.ext.web.RoutingContext

class GrfRootView : View {

    override fun render(routingContext: RoutingContext): String {
        return "Grafana /"
    }

}