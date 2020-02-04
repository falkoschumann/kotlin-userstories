package de.muspellheim.userstories.message

import de.muspellheim.userstories.eventstore.Event
import de.muspellheim.userstories.eventstore.EventStore
import de.muspellheim.userstories.message.pipeline.*

class MessagePump(private val eventStore: EventStore) {

    private val messagePipelines = mutableMapOf<Class<out Any>, Pipeline>()

    inline fun <reified M : Message> register(messageContextManager: MessageContextManager, processor: MessageProcessor) {
        register(M::class.java, messageContextManager, processor)
    }

    fun <M : Message> register(type: Class<M>, messageContextManager: MessageContextManager, processor: MessageProcessor) {
        register(
            type,
            { messageContextManager.load(it) },
            { m: Message, mc: MessageContext -> processor.process(m, mc) },
            { messageContextManager.update(it) }
        )
    }

    inline fun <reified M : Message> register(noinline load: (Message) -> MessageContext,
                                              noinline processor: (Message, MessageContext) -> Output,
                                              noinline update: (Iterable<Event>) -> Unit) {
        register(M::class.java, load, processor, update)
    }

    fun <M : Message> register(type: Class<M>,
                               load: (Message) -> MessageContext,
                               processor: (Message, MessageContext) -> Output,
                               update: (Iterable<Event>) -> Unit) {
        messagePipelines[type] = Pipeline(load, processor, update)
    }

    fun handle(inputMessage: Message): Message {
        val pipeline = messagePipelines[inputMessage.javaClass]
        return handle(pipeline!!, inputMessage)
    }

    private fun handle(pipeline: Pipeline, inputMessage: Message): Message {
        val context = pipeline.loadContext(inputMessage)
        val output = pipeline.process(inputMessage, context)
        return dispatch(output)
    }

    private fun updateContexts(events: List<Event>) {
        messagePipelines.values.forEach { it.updateContext(events) }
    }

    private fun dispatch(output: Output): Message =
        when (output) {
            is CommandOutput -> {
                eventStore.record(output.events)
                updateContexts(output.events)
                output.status
            }
            is QueryOutput -> output.result
            is NotificationOutput -> {
                output.commands.forEach { handle(it) }
                NoResponse
            }
        }

    private data class Pipeline(val loadContext: (Message) -> MessageContext,
                                val process: (Message, MessageContext) -> Output,
                                val updateContext: (Iterable<Event>) -> Unit)
}
