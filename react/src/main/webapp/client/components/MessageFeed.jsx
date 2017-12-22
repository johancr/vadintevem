import React, {Component} from 'react';
import Message from './Message.jsx';
import {getPreferredMessage} from '../actions/message.js';
import {saveHistory} from '../actions/history.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import ProgressBar from './ProgressBar.jsx';

class MessageFeed extends Component {

    constructor(props) {
        super(props);
        this.state = {
            progress: 0,
        };

        this.nextMessage = this.nextMessage.bind(this);
        this.startProgress = this.startProgress.bind(this);
        this.progress = this.progress.bind(this);
    }

    componentDidMount() {
        this.nextMessage();
    }

    componentWillUnmount() {
        clearTimeout(this.timer);
    }

    nextMessage() {
        this.props.saveHistory(this.props.message);
        this.props.getPreferredMessage(this.props.algorithm,
            () => this.startProgress());
    }

    startProgress() {
        this.setState({progress: 0});
        this.timer = setTimeout(() => this.progress(), 400);
    }

    progress() {
        const {progress} = this.state;
        if (progress < 100) {
            this.setState({progress: progress + 4});
            this.timer = setTimeout(() => this.progress(), 200);
        }
        else {
            this.nextMessage();
        }
    }

    render() {
        return (
            <div>
                <Message content={this.props.message.content}/>
                <ProgressBar progress={this.state.progress} />
            </div>
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
