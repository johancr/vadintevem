import React, {Component} from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import fetch from 'isomorphic-fetch';
import {MESSAGE_LOADED} from '../constants/actionTypes.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import Publisher from './Publisher.jsx';
import MessageFeed from './MessageFeed.jsx';
import Menu from './Menu.jsx';
import ViewSwitcher from './ViewSwitcher.jsx';
import style from 'Css/app.css';
import NotificationPresenter from './NotificationPresenter.jsx';
import {RANDOM} from '../constants/algorithms.js';
import {setAlgorithm} from '../actions/algorithm.js';
import {withRouter} from 'react-router-dom';

class App extends Component {

  componentDidMount() {
    this.props.setAlgorithm(RANDOM);
  }

  render() {
    return (
     <div className={style.container}>
        <div className={style.innerContainer}>
            <div className={style.viewSwitcher}>
                <ViewSwitcher />
            </div>
            <div className={style.notification}>
                <NotificationPresenter />
            </div>
            <div className={style.menu}>
                <Menu />
            </div>
        </div>
     </div>
     );
  }
}

App.propTypes = {
    setAlgorithm: PropTypes.func,
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        setAlgorithm,
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));
