import React, {Component} from 'react';
import Message from './Message.jsx';
import RaisedButton from 'material-ui/RaisedButton';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getNextMessage} from '../actions/message.js';

class MessagePresenter extends Component {

    constructor(props) {
        super(props);
        this.nextMessage = this.nextMessage.bind(this);
    }

    nextMessage() {
        this.props.getNextMessage();
    }

    render() {

        return (
            <div>
                <Message content={this.props.message.content}/>
                <RaisedButton onClick={this.nextMessage}
                    label='Next'
                 />
            </div>
        );
    }
}

MessagePresenter.propTypes = {
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

export default connect(mapStateToProps, mapDispatchToProps)(MessagePresenter);
