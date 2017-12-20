import {SET_ALGORITHM} from '../constants/actionTypes.js';

export default function algorithm(state = '', action) {
    switch(action.type) {
        case SET_ALGORITHM:
            return action.algorithm;
        default:
            return state;
    }
}
