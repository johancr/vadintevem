import React, {Component} from 'react';
import Message from './Message.jsx';
import {getNextMessage} from '../actions/message.js';
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
        this.props.getNextMessage();
    }

    render() {
        return (
            <Message content={this.props.message.content}/>
        );
    }
}

MessageFeed.propTypes = {
    message: PropTypes.object,
    getNextMessage: PropTypes.func,
    saveHistory: PropTypes.func,
}

const mapStateToProps = state => {
    const {message} = state;
    return {message};
}

const mapDispatchToProps = {
        getNextMessage,
        saveHistory,
};

export default connect(mapStateToProps, mapDispatchToProps)(MessageFeed);
