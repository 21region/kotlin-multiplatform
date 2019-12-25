package sample.reducers

import redux.RAction
import sample.actions.SetVisibilityFilter
import sample.enums.VisibilityFilter

fun visibilityFilter(
    state: VisibilityFilter = VisibilityFilter.SHOW_ALL,
    action: RAction
): VisibilityFilter = when (action) {
    is SetVisibilityFilter -> action.filter
    else -> state
}
