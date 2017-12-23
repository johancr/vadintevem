import React, {Component} from 'react';
import MessageInput from 'Components/MessageInput.jsx';
import Button from 'Components/Button.jsx';
import style from 'Css/publisher.css';
import {publishMessage} from '../actions/message.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {decreaseRanking} from '../actions/ranking.js';
import {getNextMessage} from '../actions/message.js';

class Publisher extends Component {

    constructor(props) {
        super(props);
        this.publish = this.publish.bind(this);
        this.onChange = this.onChange.bind(this);
        this.state = {text: ''};
    }

    publish() {
        this.props.publishMessage(this.state.text,
            () => {
                this.props.decreaseRanking(this.props.message);
                this.props.getNextMessage();
                this.setState({text: ''});
            });
    }

    onChange(event) {
        this.setState({text: event.target.value});
    }

    render() {
        return (
            <div className={style.container}>
                <MessageInput onChange={this.onChange} text={this.state.text} />
                <Button onClick={this.publish} label = 'Publish'/>
            </div>
        );
    }
}

Publisher.propTypes = {
    publishMessage: PropTypes.func,
    decreaseRanking: PropTypes.func,
    message: PropTypes.object,
    getNextMessage: PropTypes.func,
}

const mapStateToProps = state => {
    const {message} = state;
    return {message};
}

const mapDispatchToProps = {
        publishMessage,
        decreaseRanking,
        getNextMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(Publisher);
