import React from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import fetch from 'isomorphic-fetch';
import {MESSAGE_LOADED} from '../constants/actionTypes.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import AppBar from 'material-ui/AppBar'

class App extends React.Component {

  componentDidMount() {
    this.props.loadMessage();
  }

  render() {
    return (
     <div>
        <AppBar title='VIV'
                showMenuIconButton={false}>
        </AppBar>
        <MessagePresenter />
     </div>
     );
  }
}

App.propTypes = {
    loadMessage: PropTypes.func
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = dispatch => {
    return {
        loadMessage: () => {
            fetch('/service/message')
                .then(response => response.json())
                .then(json => dispatch({type: MESSAGE_LOADED, message: json}));
        }
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
