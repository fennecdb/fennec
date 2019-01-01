package db.fennec.api.web.grafana

import db.fennec.api.web.View
import io.vertx.ext.web.RoutingContext

class GrfTagsKeysView : View {
    override fun render(routingContext: RoutingContext): String {
        return "[\n" +
                "    {\"type\":\"string\",\"text\":\"City\"},\n" +
                "    {\"type\":\"string\",\"text\":\"Country\"}\n" +
                "]"
    }

}