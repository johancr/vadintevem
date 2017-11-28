import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import message from './reducers/message.js';
import {createStore, combineReducers, applyMiddleware} from 'redux';
import thunkMiddleware from 'redux-thunk';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import style from './css/index.css';


let reducers = combineReducers({message});
let store = createStore(reducers, {}, applyMiddleware(thunkMiddleware),
                                        window.devToolsExtension
                                            ? window.devToolsExtension()
                                            : f => f);
ReactDOM.render(
    <Provider store={store}>
        <MuiThemeProvider>
            <div className={style.container}>
                <App/>
            </div>
        </MuiThemeProvider>
    </Provider>
    , document.getElementById('root'));
