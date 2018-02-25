import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import message from './reducers/message.js';
import isLoadingMessage from './reducers/isLoadingMessage.js';
import {createStore, combineReducers, applyMiddleware, compose} from 'redux';
import thunkMiddleware from 'redux-thunk';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import style from 'Css/index.css';
import commonStyle from './css/index.css';
import history from './reducers/history.js';
import ranking from './reducers/ranking.js';
import notification from './reducers/notification.js';
import algorithm from './reducers/algorithm.js';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import authentication from './reducers/authentication.js';
import authored from './reducers/authored.js';
import event from './reducers/event.js';
import {HashRouter as Router} from 'react-router-dom';


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

let reducers = combineReducers({
    message,
    isLoadingMessage,
    history,
    ranking,
    notification,
    algorithm,
    authentication,
    authored,
    event,
});
let store = createStore(reducers, {}, composeEnhancers(applyMiddleware(thunkMiddleware)));

const muiTheme = getMuiTheme({
    fontFamily: 'Roboto,"Helvetica Neue",Helvetica,Arial,sans-serif'
});

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <MuiThemeProvider muiTheme={muiTheme}>
                <div className={style.container}>
                    <App/>
                </div>
            </MuiThemeProvider>
        </Router>
    </Provider>
    , document.getElementById('root'));
