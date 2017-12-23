import React, {Component} from 'react';
import Message from 'Components/Message.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getNextMessage} from '../actions/message.js';
import Button from 'Components/Button.jsx';
import {saveHistory} from '../actions/history.js';
import {increaseRanking} from '../actions/ranking.js';
import style from '../css/messagePresenter.css';

class MessagePresenter extends Component {

    constructor(props) {
        super(props);
        this.nextMessage = this.nextMessage.bind(this);
    }

    nextMessage() {
        this.props.saveHistory(this.props.message);
        this.props.increaseRanking(this.props.message);
        this.props.getNextMessage();
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
    getNextMessage: PropTypes.func,
    saveHistory: PropTypes.func,
    increaseRanking: PropTypes.func,
}

const mapStateToProps = state => {
    const {message} = state;
    return {message};
}

const mapDispatchToProps = {
        getNextMessage,
        saveHistory,
        increaseRanking,
};

export default connect(mapStateToProps, mapDispatchToProps)(MessagePresenter);
