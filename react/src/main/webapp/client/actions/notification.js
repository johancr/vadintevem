import {NOTIFICATION,
        RESET_NOTIFICATION,
        } from '../constants/actionTypes.js';
import moment from 'moment';

export function notify(notification) {
    return dispatch => dispatch(
            {
                type: NOTIFICATION,
                text: notification,
                timestamp: moment(),
            });
}

export function reset() {
    return dispatch => dispatch(
            {
                type: RESET_NOTIFICATION,
            });
}

