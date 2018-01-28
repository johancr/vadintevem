import {LOAD_AUTHORED,
        AUTHORED_LOADED,
        } from '../constants/actionTypes.js';
import {notify} from './notification.js';

export function loadAuthored(username) {
    return dispatch => {
        dispatch({type: LOAD_AUTHORED});
        return fetch(`/service/author/${username}/message`)
            .then(response => {
                if (response.ok) {
                    response.json().then(json => dispatch({type: AUTHORED_LOADED, authored: json}));
                }
                else {
                    notify('No authored messages found')(dispatch);
                }
            });
    };
}
