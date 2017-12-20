import {LOAD_MESSAGE,
        MESSAGE_LOADED,
        PUBLISH_MESSAGE,
        MESSAGE_PUBLISHED,
        NOTIFICATION,
        MESSAGE_NOT_FOUND,
        } from '../constants/actionTypes.js';
import {notify} from './notification.js';

export function getNextMessage() {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch('/service/message')
            .then(response => {
                if (response.ok) {
                    response.json().then(json => dispatch({type: MESSAGE_LOADED, message: json}));
                }
                else {
                    notify('No unread messages found')(dispatch);
                }
            });
    };
}

export function getPreferredMessage(algorithm) {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch(`/service/message?algorithm=${algorithm}`)
            .then(response => {
                if (response.ok) {
                    response.json().then(json => dispatch({type: MESSAGE_LOADED, message: json}));
                }
                else {
                    notify('No unread messages found')(dispatch);
                }
            });
    };
}

export function publishMessage(message) {
    return dispatch => {
        dispatch({type: PUBLISH_MESSAGE});
        return fetch('/service/message',
                   { method: 'POST',
                     body: JSON.stringify({content: message}),
                     headers: { "Content-Type": "application/json" }
                   })
            .then((response) => {
                dispatch({type: MESSAGE_PUBLISHED});
                if (!response.ok) {
                    response.json().then(data => dispatch(
                        {
                            type: NOTIFICATION,
                            text: data.errors
                        }));
                }
            });
    };
}