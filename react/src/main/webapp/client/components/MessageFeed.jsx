import React, {Component} from 'react';
import Message from './Message.jsx';
import {getPreferredMessage} from '../actions/message.js';
import {saveHistory} from '../actions/history.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class MessageFeed extends Component {

    constructor(props) {
        super(props);
        this.state = {intervalId: ''};

        this.nextMessage = this.nextMessage.bind(this);
    }

    componentDidMount() {
        const intervalId = setInterval(() => this.nextMessage(), 5000);
        this.setState({intervalId: intervalId});
    }

    componentWillUnmount() {
        clearInterval(this.state.intervalId);
    }

    nextMessage() {
        this.props.saveHistory(this.props.message);
        this.props.getPreferredMessage(this.props.algorithm);
    }

    render() {
        return (
            <Message content={this.props.message.content}/>
        );
    }
}

MessageFeed.propTypes = {
    message: PropTypes.object,
    getPreferredMessage: PropTypes.func,
    saveHistory: PropTypes.func,
    algorithm: PropTypes.string,
}

const mapStateToProps = state => {
    const {message, algorithm} = state;
    return {message, algorithm};
}

const mapDispatchToProps = {
        getPreferredMessage,
        saveHistory,
};

export default connect(mapStateToProps, mapDispatchToProps)(MessageFeed);
