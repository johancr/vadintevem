import {HISTORY_LOADED} from '../constants/actionTypes.js';

export default function message(state = [], action) {
    switch(action.type) {
        case HISTORY_LOADED:
            return action.history;
        default:
            return state;
    }
}
