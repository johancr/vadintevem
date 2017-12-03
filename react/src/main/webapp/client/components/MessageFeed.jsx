import React, {Component} from 'react';
import Message from './Message.jsx';
import {getNextMessage} from '../actions/message.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class MessageFeed extends Component {

    constructor(props) {
        super(props);
        this.state = {intervalId: ''};

        this.nextMessage = this.nextMessage.bind(this);
    }

    componentDidMount() {
        const intervalId = setInterval(() => this.nextMessage(), 10000);
        this.setState({intervalId: intervalId});
    }

    componentWillUnmount() {
        clearInterval(this.state.intervalId);
    }

    nextMessage() {
        this.props.getNextMessage();
    }

    render() {
        return (
            <div>
                <Message content={this.props.message.content}/>
            </div>
        );
    }
}

MessageFeed.propTypes = {
    message: PropTypes.object,
    getNextMessage: PropTypes.func
}

const mapStateToProps = state => {
    const {message} = state;
    return {message};
}

const mapDispatchToProps = {
        getNextMessage
};

export default connect(mapStateToProps, mapDispatchToProps)(MessageFeed);
