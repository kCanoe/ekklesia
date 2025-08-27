package com.kcanoe.ekklesia

import com.kcanoe.ekklesia.api.greet
import com.kcanoe.ekklesia.api.measures
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.LocalPathContent
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import java.nio.file.Path
import kotlin.time.ExperimentalTime

object AssetPaths {
    const val INDEX_PATH: String = "./src/main/resources/site/index.html"
    const val STYLES_PATH: String = "./src/main/resources/site/styles.css"
}

fun Routing.pageRoutes() {
    route("/") {
        get {
            val filePath = Path.of(AssetPaths.INDEX_PATH)
            call.respond(HttpStatusCode.OK, LocalPathContent(filePath))
        }
        get("/styles.css") {
            val filePath = Path.of(AssetPaths.STYLES_PATH)
            call.respond(HttpStatusCode.OK, LocalPathContent(filePath))
        }
    }
}

@OptIn(ExperimentalTime::class)
fun Routing.apiRoutes() {
    route("/api") {
        get("/greet") {
            val responseView = greet()
            call.respond(HttpStatusCode.OK, responseView)
        }
        get("/measures") {
            val measures = measures()
            call.respond(HttpStatusCode.OK, measures)
        }
    }
}

fun createServer(): EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration> =
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1"
    ) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            allowHost("localhost:5173")
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Accept)
            allowMethod(HttpMethod.Get)
        }
        routing {
            pageRoutes()
            apiRoutes()
        }
    }

fun main() {
    val server = createServer()
    server.start(wait = true)
}
