package de.muspellheim.userstories.util

typealias ActionHandler<T> = (T) -> Unit

class Action<T> {

    private var handlers = listOf<ActionHandler<T>>()

    operator fun plusAssign(handler: ActionHandler<T>) {
        handlers = handlers + handler
    }

    operator fun minusAssign(handler: ActionHandler<T>) {
        handlers = handlers - handler
    }

    operator fun invoke(message: T) {
        handlers.forEach { it(message) }
    }
}
