package sample

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(vertical = 16.px)

                backgroundColor = Color.green
            }
            +"Hello world!"
        }
    }
}
