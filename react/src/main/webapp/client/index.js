import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import messages from './reducers/messages.js';
import {createStore} from 'redux';
import { combineReducers } from 'redux';

let reducers = combineReducers({messages});
let store = createStore(reducers, window.devToolsExtension
                                    ? window.devToolsExtension()
                                    : f => f);
ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
    , document.getElementById('root'));