import {MESSAGE_LOADED} from '../constants/actionTypes.js';

export default function message(state = {}, action) {
    switch(action.type) {
        case MESSAGE_LOADED:
            return action.message;
        default:
            return state;
    }
}