import {LOAD_HISTORY,
        HISTORY_LOADED,
        SAVE_HISTORY,
        HISTORY_SAVED,
        } from '../constants/actionTypes.js';

export function loadHistory() {
    return dispatch => {
        dispatch({type: LOAD_HISTORY});
        return fetch('/service/history')
            .then(response => response.json())
            .then(json => dispatch({type: HISTORY_LOADED, history: json}));
    };
}

