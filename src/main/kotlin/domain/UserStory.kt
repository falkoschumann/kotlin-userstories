/*
 * User Stories
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.userstories.domain

data class UserStory(
    val id: Int,
    val theme: String,
    val title: String,
    val description: String,
    val notes: String,
    val priority: Priority,
    val status: Status,
    val estimate: Int // 1, 2, 3, 5, 8, 13, 21
)

enum class Priority {
    MUST_HAVE, SHOULD_HAVE, COULD_HAVE, WONT_HAVE
}

enum class Status {
    OPEN, READY, IN_PROGRESS, DONE
}

class StoryCreated
class StoryUpdated
class StoryDisassembled
class StoryCommitted
class StoryStarted
class StoryCompleted

// Event Store: CSV
// Timestamp, Event Type, Event Data (JSON)
