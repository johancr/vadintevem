import React, {Component} from 'react';
import Message from 'Components/Message.jsx';
import {findMessage} from '../actions/message.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import ProgressBar from 'Components/ProgressBar.jsx';

class MessageFeed extends Component {

    constructor(props) {
        super(props);
        this.state = {
            progress: 0,
        };

        this.findMessage = this.findMessage.bind(this);
        this.startProgress = this.startProgress.bind(this);
        this.progress = this.progress.bind(this);
    }

    componentDidMount() {
        this.findMessage();
    }

    componentWillUnmount() {
        clearTimeout(this.timer);
    }

    findMessage() {
        this.props.findMessage(this.props.algorithm,
            this.props.message,
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
            this.findMessage();
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
    findMessage: PropTypes.func,
    algorithm: PropTypes.string,
}

const mapStateToProps = state => {
    const {message, algorithm} = state;
    return {message, algorithm};
}

const mapDispatchToProps = {
        findMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(MessageFeed);
