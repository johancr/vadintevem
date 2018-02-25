import {SET_ALGORITHM, LOG_OUT} from '../constants/actionTypes.js';
import {RANDOM, UNREAD} from '../constants/algorithms.js';

export default function algorithm(state = '', action) {
    switch(action.type) {
        case SET_ALGORITHM:
            return action.algorithm;
        case LOG_OUT:
            return state === UNREAD ? RANDOM : state;
        default:
            return state;
    }
}
