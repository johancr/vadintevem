import React, {Component} from 'react';
import Message from 'Components/Message.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {nextMessage, findMessage} from '../actions/message.js';
import Button from 'Components/Button.jsx';
import style from 'Css/messagePresenter.css';
import _ from 'lodash';


class MessagePresenter extends Component {

    constructor(props) {
        super(props);
        this.nextMessage = this.nextMessage.bind(this);
    }

    componentDidMount() {
        const {username} = this.props.authentication;
        if (_.isEmpty(this.props.message)) {
            this.props.findMessage('UNREAD', username);
        }
    }

    nextMessage() {
        const {username} = this.props.authentication;
        this.props.nextMessage('UNREAD', username, this.props.message);
    }

    render() {

        return (
            <div className={style.container}>
                <Message content={this.props.message.content}/>
                <Button onClick={this.nextMessage}
                    label = 'Next'
                />
            </div>
        );
    }
}

MessagePresenter.propTypes = {
    message: PropTypes.object,
    nextMessage: PropTypes.func,
    findMessage: PropTypes.func,
    authentication: PropTypes.object,
}

const mapStateToProps = state => {
    const {message, authentication} = state;
    return {message, authentication};
}

const mapDispatchToProps = {
        nextMessage,
        findMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(MessagePresenter);
