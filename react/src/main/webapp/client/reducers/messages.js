import {MESSAGES_LOADED} from '../constants/actionTypes.js';

export default function messages(state = [{content: 'Message'}], action) {
    switch(action.type) {
        case MESSAGES_LOADED:
            return state;
        default:
            return state;
    }
}