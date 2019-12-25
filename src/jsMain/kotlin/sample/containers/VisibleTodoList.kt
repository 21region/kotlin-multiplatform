package sample.containers

import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.WrapperAction
import sample.actions.ToggleTodoAction
import sample.components.TodoList
import sample.components.TodoListProps
import sample.entities.Todo
import sample.enums.VisibilityFilter
import sample.reducers.State

private fun getVisibleTodos(todos: Array<Todo>, filter: VisibilityFilter): Array<Todo> = when (filter) {
    VisibilityFilter.SHOW_ALL -> todos
    VisibilityFilter.SHOW_ACTIVE -> todos.filter { !it.completed }.toTypedArray()
    VisibilityFilter.SHOW_COMPLETED -> todos.filter { it.completed }.toTypedArray()
}

private interface TodoListStateProps : RProps {
    var todos: Array<Todo>
}

private interface TodoListDispatchProps : RProps {
    var toggleTodo: (Int) -> Unit
}

val visibleTodoList: RClass<RProps> =
    rConnect<State, ToggleTodoAction, WrapperAction, RProps, TodoListStateProps, TodoListDispatchProps, TodoListProps>(
        { state, _ ->
            todos = getVisibleTodos(state.todos, state.visibilityFilter)
        },
        { dispatch, _ ->
            toggleTodo = { dispatch(ToggleTodoAction(it)) }
        }
    )(TodoList::class.js.unsafeCast<RClass<TodoListProps>>())
