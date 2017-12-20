import {SET_ALGORITHM} from '../constants/actionTypes.js';

export function setAlgorithm(algorithm) {
    return dispatch => {
        dispatch({type: SET_ALGORITHM, algorithm: algorithm});
    };
}
