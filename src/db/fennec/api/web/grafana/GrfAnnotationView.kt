package db.fennec.api.web.grafana

import db.fennec.api.web.View
import io.vertx.ext.web.RoutingContext

class GrfAnnotationView : View {
    override fun render(routingContext: RoutingContext): String {
        return "[\n" +
                "  {\n" +
                "    annotation: annotation, // The original annotation sent from Grafana.\n" +
                "    time: time, // Time since UNIX Epoch in milliseconds. (required)\n" +
                "    title: title, // The title for the annotation tooltip. (required)\n" +
                "    tags: tags, // Tags for the annotation. (optional)\n" +
                "    text: text // Text for the annotation. (optional)\n" +
                "  }\n" +
                "]"
    }

}