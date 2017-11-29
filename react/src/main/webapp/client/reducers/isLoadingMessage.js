import {LOAD_MESSAGE, MESSAGE_LOADED} from '../constants/actionTypes.js';

export default function isMessageLoading(state = false, action) {
    switch(action.type) {
        case LOAD_MESSAGE:
            return true;
        case MESSAGE_LOADED:
            return false;
        default:
            return state;
    }
}
