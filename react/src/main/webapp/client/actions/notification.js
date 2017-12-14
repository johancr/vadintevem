import {NOTIFICATION,
        RESET_NOTIFICATION,
        } from '../constants/actionTypes.js';

export function notify(notification) {
    return dispatch => dispatch(
            {
                type: NOTIFICATION,
                text: notification,
            });
}

export function reset() {
    return dispatch => dispatch(
            {
                type: RESET_NOTIFICATION,
            });
}

