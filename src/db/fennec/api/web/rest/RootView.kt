package db.fennec.api.web.rest

import db.fennec.Launcher
import db.fennec.api.web.View
import io.vertx.ext.web.RoutingContext
import j2html.TagCreator.*

class RootView : View {

    override fun render(routingContext: RoutingContext): String {
        return html(
                head(
                        title("/ - Fennec"),
                        link().withRel("stylesheet").withHref("/style/main.css"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/css?family=Noto+Sans")
                ),
                body(
                        main(attrs("#main.content"),
                                h1("Fennec ${Launcher.VERSION} REST API")
                        )
                )
        ).render()
    }

}