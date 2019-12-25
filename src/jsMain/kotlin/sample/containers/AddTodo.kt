package sample.containers

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.button
import react.dom.div
import react.dom.form
import react.dom.input
import react.redux.rConnect
import redux.WrapperAction
import sample.actions.AddTodoAction
import sample.store

class AddTodo(props: RProps) : RComponent<RProps, RState>(props) {
    private val inputRef = createRef<HTMLInputElement>()
    override fun RBuilder.render() {
        div {
            form {
                attrs.onSubmitFunction = { event ->
                    event.preventDefault()
                    inputRef.current!!.let {
                        if (it.value.trim().isNotEmpty()) {
                            store.dispatch(AddTodoAction(it.value))
                            it.value = ""
                        }
                    }
                }
                input(type = InputType.text) {
                    ref = inputRef
                }
                button(type = ButtonType.submit) {
                    +"Add Todo"
                }
            }
        }
    }
}

val addTodo: RClass<RProps> = rConnect<AddTodo, WrapperAction>()(AddTodo::class.js.unsafeCast<RClass<RProps>>())
