import {NOTIFICATION,
        RESET_NOTIFICATION,
        } from '../constants/actionTypes.js';

export default function message(state = '', action) {
    switch(action.type) {
        case NOTIFICATION:
            return action.text;
        case RESET_NOTIFICATION:
            return '';
        default:
            return state;
    }
}
