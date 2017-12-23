import {NOTIFICATION,
        RESET_NOTIFICATION,
        } from '../constants/actionTypes.js';
import moment from 'moment';

const initialState = {
    text: '',
    timestamp: moment().subtract(moment.duration(3, 'seconds')),
};

export default function message(state = initialState, action) {
    switch(action.type) {
        case NOTIFICATION:
            return {
                    text: action.text,
                    timestamp: action.timestamp,
                   };
        case RESET_NOTIFICATION:
            return passedLimit(state.timestamp)
                ? initialState
                : state
        default:
            return state;
    }
}

function passedLimit(timestamp) {
    return timestamp < moment().subtract(moment.duration(3, 'seconds'));
}
