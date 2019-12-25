package sample.reducers

import redux.RAction
import sample.actions.AddTodoAction
import sample.actions.ToggleTodoAction
import sample.entities.Todo

fun todos(state: Array<Todo> = emptyArray(), action: RAction): Array<Todo> = when (action) {
    is AddTodoAction -> state + Todo(action.id, action.text, false)
    is ToggleTodoAction -> state.map {
        if (it.id == action.id) {
            it.copy(completed = !it.completed)
        } else {
            it
        }
    }.toTypedArray()
    else -> state
}
