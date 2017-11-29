import {LOAD_MESSAGE, MESSAGE_LOADED} from '../constants/actionTypes.js';

export function getNextMessage() {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch('/service/message')
            .then(response => response.json())
            .then(json => dispatch({type: MESSAGE_LOADED, message: json}));
    }
}