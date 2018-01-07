import {LOG_IN} from '../constants/actionTypes.js';
import {LOG_OUT} from '../constants/actionTypes.js';

const initialState = {
    authenticated: false,
    username: 'unknown',
};

export default function algorithm(state = initialState, action) {
    switch(action.type) {
        case LOG_IN:
            return {authenticated: true, username: action.username};
        case LOG_OUT:
            return initialState;
        default:
            return state;
    }
}
