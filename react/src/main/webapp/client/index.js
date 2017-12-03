import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import message from './reducers/message.js';
import isLoadingMessage from './reducers/isLoadingMessage.js';
import {createStore, combineReducers, applyMiddleware, compose} from 'redux';
import thunkMiddleware from 'redux-thunk';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import style from './css/index.css';
import view from './reducers/view.js';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

let reducers = combineReducers({message, isLoadingMessage, view});
let store = createStore(reducers, {}, composeEnhancers(applyMiddleware(thunkMiddleware)));
ReactDOM.render(
    <Provider store={store}>
        <MuiThemeProvider>
            <div className={style.container}>
                <App/>
            </div>
        </MuiThemeProvider>
    </Provider>
    , document.getElementById('root'));
