package trading

import trading.infrastructure.ApiInteractor
import trading.models.AuthParameters

fun main(args: Array<String>) {

    val authParameters = AuthParameters(
        ssoToken = args[0],
        sandboxMode = true
    )

    ApiInteractor().runSafely(authParameters) { api ->
        val app = App(api)
        app.start()
    }
}
