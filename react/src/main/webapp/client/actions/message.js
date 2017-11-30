import {LOAD_MESSAGE,
        MESSAGE_LOADED,
        PUBLISH_MESSAGE,
        MESSAGE_PUBLISHED,
        } from '../constants/actionTypes.js';

export function getNextMessage() {
    return dispatch => {
        dispatch({type: LOAD_MESSAGE});
        return fetch('/service/message')
            .then(response => response.json())
            .then(json => dispatch({type: MESSAGE_LOADED, message: json}));
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
            .then(() => dispatch({type: MESSAGE_PUBLISHED}))
    };
}