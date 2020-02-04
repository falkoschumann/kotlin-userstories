package de.muspellheim.userstories.message.pipeline

import de.muspellheim.userstories.eventstore.Event
import de.muspellheim.userstories.message.Message

interface MessageContext

interface MessageContextBuilder {
    fun update(events: Iterable<Event>)
}

interface MessageContextLoader {
    fun load(input: Message): MessageContext
}

interface MessageContextManager : MessageContextLoader, MessageContextBuilder
