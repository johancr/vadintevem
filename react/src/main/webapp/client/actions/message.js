import {LOAD_MESSAGE,
        MESSAGE_LOADED,
        PUBLISH_MESSAGE,
        MESSAGE_PUBLISHED,
        NOTIFICATION,
        MESSAGE_NOT_FOUND,
        } from '../constants/actionTypes.js';
import {notify} from './notification.js';

export function findMessage(algorithm, username, nextAction = () => {}) {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch(`/service/message/find?algorithm=${algorithm}&username=${username}`)
            .then(response => {
                if (response.ok) {
                    response.json().then(json => dispatch({type: MESSAGE_LOADED, message: json}));
                }
                else {
                    notify('No unread messages found')(dispatch);
                    dispatch({type: MESSAGE_NOT_FOUND});
                }
            })
            .then(nextAction());
    };
}

export function nextMessage(algorithm, username, previous, nextAction = () => {}) {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch(`/service/message/next?algorithm=${algorithm}&username=${username}`,
                    { method: 'POST',
                      body: JSON.stringify(previous),
                      headers: { "Content-Type": "application/json" }
                    })
            .then(response => {
                if (response.ok) {
                    response.json().then(json => dispatch({type: MESSAGE_LOADED, message: json}));
                }
                else {
                    notify('No unread messages found')(dispatch);
                    dispatch({type: MESSAGE_NOT_FOUND});
                }
            })
            .then(nextAction());
    };
}

export function publishMessage(message, username, nextAction = () => {}) {
    return dispatch => {
        dispatch({type: PUBLISH_MESSAGE});
        return fetch('/service/message',
                   { method: 'POST',
                     body: JSON.stringify({message: {content: message}, user:{username: username}}),
                     headers: { "Content-Type": "application/json" }
                   })
            .then((response) => {
                dispatch({type: MESSAGE_PUBLISHED});
                if (response.ok) {
                    nextAction();
                }
                else {
                    response.json().then(data => notify(data.errors)(dispatch));
                }
            });
    };
}

export function setMessage(message) {
    return dispatch => dispatch({type: MESSAGE_LOADED, message: message});
}