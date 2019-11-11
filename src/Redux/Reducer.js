import React from 'react'
import {LOG_IN, SIGN_UP} from './Actions'

const initialState = {
    visibilityFilter: VisibilityFilters.SHOW_ALL,
    todos: []
}

function todoApp(state = initialState, action) {
    switch (action) {
      case LOG_IN:
        return state;
      case SIGN_UP:
          return state;
      default:
        return state
    }
}
