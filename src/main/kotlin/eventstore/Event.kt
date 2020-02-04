package de.muspellheim.userstories.eventstore

import java.time.Instant
import java.util.*

abstract class Event(var id: String = UUID.randomUUID().toString(), var timestamp: Instant = Instant.now())
