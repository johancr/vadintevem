import {AUTHORED_LOADED} from '../constants/actionTypes.js';

export default function authored(state = [], action) {
    switch(action.type) {
        case AUTHORED_LOADED:
            return action.authored;
        default:
            return state;
    }
}
