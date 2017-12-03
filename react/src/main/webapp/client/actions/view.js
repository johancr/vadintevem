import {SET_VIEW} from '../constants/actionTypes.js';

export function setView(view) {
    return dispatch => {
        dispatch({type: SET_VIEW, view: view});
    };
}
