package de.muspellheim.userstories.eventstore

import de.muspellheim.userstories.util.Action

interface EventStore {

    val onRecorded: Action<List<Event>>

    fun record(event: Event)
    fun record(events: List<Event>)

    fun replay(): Iterable<Event>
    fun replay(eventTypes: List<Class<Any>>): Iterable<Event>
}
