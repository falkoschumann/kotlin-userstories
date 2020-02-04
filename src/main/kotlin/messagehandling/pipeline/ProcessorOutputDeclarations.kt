package de.muspellheim.userstories.message.pipeline

import de.muspellheim.userstories.eventstore.Event
import de.muspellheim.userstories.message.Command
import de.muspellheim.userstories.message.CommandStatus
import de.muspellheim.userstories.message.QueryResult

sealed class Output
data class CommandOutput(val status: CommandStatus, val events: List<Event> = emptyList()) : Output()
data class NotificationOutput(val commands: List<Command>) : Output()
data class QueryOutput(val result: QueryResult) : Output()
