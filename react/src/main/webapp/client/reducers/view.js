import {SET_VIEW} from '../constants/actionTypes.js';
import {READER_VIEW} from '../constants/views.js';

export default function message(state = READER_VIEW, action) {
    switch(action.type) {
        case SET_VIEW:
            return action.view;
        default:
            return state;
    }
}
