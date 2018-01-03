import {MESSAGE_LOADED,
        MESSAGE_NOT_FOUND,
} from '../constants/actionTypes.js';

export default function message(state = {}, action) {
    switch(action.type) {
        case MESSAGE_LOADED:
            return action.message;
        case MESSAGE_NOT_FOUND:
            return {};
        default:
            return state;
    }
}