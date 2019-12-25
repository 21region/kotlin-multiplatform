package sample.actions

import redux.RAction
import sample.enums.VisibilityFilter

class SetVisibilityFilter(val filter: VisibilityFilter) : RAction

class AddTodoAction(val text: String): RAction {
    private companion object {
        var nextTodoId = 1
    }
    val id = nextTodoId++
}

class ToggleTodoAction(val id: Int): RAction
