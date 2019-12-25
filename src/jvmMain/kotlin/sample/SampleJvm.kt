package sample

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import java.io.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Hello from Ktor!")
                    }
                    body {
                        div {
                            id = "root"
                        }
                        script(src = "/static/js-jvm.js") {}
                    }
                }
            }

            static("/static") {
                resource("js-jvm.js")
            }
        }
    }.start(wait = true)
}
