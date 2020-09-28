package trading

import org.reactivestreams.example.unicast.AsyncSubscriber
import ru.tinkoff.invest.openapi.models.streaming.StreamingEvent
import java.util.concurrent.Executor
import java.util.logging.Logger

internal class StreamingApiSubscriber(
    private val logger: Logger,
    executor: Executor
) :
    AsyncSubscriber<StreamingEvent>(executor) {

    override fun whenNext(event: StreamingEvent): Boolean {
        logger.info("Пришло новое событие из Streaming API\n$event")
        return true
    }
}
