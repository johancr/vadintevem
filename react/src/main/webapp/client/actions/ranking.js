import {LOAD_TOP,
        TOP_LOADED,
        } from '../constants/actionTypes.js';


export function loadTop(limit) {
    return dispatch => {
        dispatch({type: LOAD_TOP});
        return fetch(`/service/ranking?limit=${limit}`)
               .then(response => response.json())
               .then(json => dispatch({type: TOP_LOADED, top: json}));
    }
}
