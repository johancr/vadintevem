import React from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import fetch from 'isomorphic-fetch';
import {MESSAGE_LOADED} from '../constants/actionTypes.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getNextMessage} from '../actions/message.js';
import Publisher from './Publisher.jsx';
import MessageFeed from './MessageFeed.jsx';
import Menu from './Menu.jsx';
import ViewSwitcher from './ViewSwitcher.jsx';
import style from '../css/app.css';
import NotificationPresenter from './NotificationPresenter.jsx';
import {RANDOM} from '../constants/algorithms.js';
import {setAlgorithm} from '../actions/algorithm.js';

class App extends React.Component {

  componentDidMount() {
    this.props.setAlgorithm(RANDOM);
    this.props.getNextMessage();
  }

  render() {
    return (
     <div className={style.container}>
        <Menu />
        <div className={style.viewSwitcher}>
            <ViewSwitcher />
        </div>
        <div className={style.notification}>
            <NotificationPresenter />
        </div>
     </div>
     );
  }
}

App.propTypes = {
    getNextMessage: PropTypes.func,
    setAlgorithm: PropTypes.func,
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        getNextMessage,
        setAlgorithm,
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
