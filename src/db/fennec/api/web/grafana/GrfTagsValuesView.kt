package db.fennec.api.web.grafana

import db.fennec.api.web.View
import io.vertx.ext.web.RoutingContext

class GrfTagsValuesView : View {
    override fun render(routingContext: RoutingContext): String {
        return "[\n" +
                "    {'text': 'Eins!'},\n" +
                "    {'text': 'Zwei'},\n" +
                "    {'text': 'Drei!'}\n" +
                "]"
    }

}