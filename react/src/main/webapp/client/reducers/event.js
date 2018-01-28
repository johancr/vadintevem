import {MESSAGE_READ_EVENT} from '../constants/actionTypes.js';
import moment from 'moment';

const initialState = {
    messageId: undefined,
    timestamp: moment().subtract(moment.duration(3, 'seconds')),
};

export default function event(state = initialState, action) {
    switch(action.type) {
        case MESSAGE_READ_EVENT:
            return {
                    messageId: action.messageId,
                    timestamp: action.timestamp,
                   };
        default:
            return state;
    }
}
