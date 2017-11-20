import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import message from './reducers/message.js';
import {createStore, combineReducers, applyMiddleware} from 'redux';
import thunkMiddleware from 'redux-thunk';

let reducers = combineReducers({message});
let store = createStore(reducers, {}, applyMiddleware(thunkMiddleware),
                                        window.devToolsExtension
                                            ? window.devToolsExtension()
                                            : f => f);
ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
    , document.getElementById('root'));