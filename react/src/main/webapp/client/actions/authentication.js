import {LOG_IN} from '../constants/actionTypes.js';
import {LOG_OUT} from '../constants/actionTypes.js';

export function logIn(username) {
    return dispatch => {
        dispatch({type: LOG_IN, username: username});
    };
}

export function logOut() {
    return dispatch => {
        dispatch({type: LOG_OUT});
    };
}
