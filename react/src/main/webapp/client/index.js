import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import message from './reducers/message.js';
import {createStore} from 'redux';
import { combineReducers } from 'redux';

let reducers = combineReducers({message});
let store = createStore(reducers, window.devToolsExtension
                                    ? window.devToolsExtension()
                                    : f => f);
ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
    , document.getElementById('root'));