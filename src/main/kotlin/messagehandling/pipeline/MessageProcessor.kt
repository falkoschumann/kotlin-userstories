package de.muspellheim.userstories.message.pipeline

import de.muspellheim.userstories.message.Message

interface MessageProcessor {
    fun process(input: Message, model: MessageContext): Output
}
