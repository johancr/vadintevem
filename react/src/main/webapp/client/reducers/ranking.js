import {TOP_LOADED} from '../constants/actionTypes.js';

export default function message(state = [], action) {
    switch(action.type) {
        case TOP_LOADED:
            return action.top;
        default:
            return state;
    }
}
