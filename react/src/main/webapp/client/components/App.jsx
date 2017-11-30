import React from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import fetch from 'isomorphic-fetch';
import {MESSAGE_LOADED} from '../constants/actionTypes.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import AppBar from 'material-ui/AppBar'
import {getNextMessage} from '../actions/message.js';
import Publisher from './Publisher.jsx';

class App extends React.Component {

  componentDidMount() {
    this.props.getNextMessage();
  }

  render() {
    return (
     <div>
        <AppBar title='VIV'
                showMenuIconButton={false}>
        </AppBar>
        <MessagePresenter />
        <Publisher />
     </div>
     );
  }
}

App.propTypes = {
    getNextMessage: PropTypes.func
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        getNextMessage
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
