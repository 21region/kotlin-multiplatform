package sample

import react.dom.render
import react.redux.provider
import redux.RAction
import redux.compose
import redux.createStore
import redux.rEnhancer
import sample.components.app
import sample.reducers.State
import sample.reducers.combinedReducers
import kotlin.browser.*

val store = createStore<State, RAction, dynamic>(
    combinedReducers(), State(), compose(
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)

fun main() {
    val rootDiv = document.getElementById("root")
    render(rootDiv) {
        provider(store) {
            app()
        }
    }
}

