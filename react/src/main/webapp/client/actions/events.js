import {
MESSAGE_READ_EVENT
} from '../constants/actionTypes.js';
import moment from 'moment';

export function listenForEvents(username) {
    return dispatch => {
        const ws = new WebSocket(`ws://localhost:8080/ws/events?username=${username}`);
        ws.onmessage = function(event) {
            const msg = JSON.parse(event.data);
            dispatch(
                {
                    type: MESSAGE_READ_EVENT,
                    messageId: msg.data.messageId,
                    timestamp: moment(),
                });
        };
    };
}
