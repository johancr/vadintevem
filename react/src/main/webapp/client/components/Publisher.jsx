import React, {Component} from 'react';
import MessageInput from './MessageInput.jsx';
import Button from './Button.jsx';
import style from '../css/publisher.css';
import {publishMessage} from '../actions/message.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class Publisher extends Component {

    constructor(props) {
        super(props);
        this.publish = this.publish.bind(this);
        this.onChange = this.onChange.bind(this);
        this.state = {text: ''};
    }

    publish() {
        this.props.publishMessage(this.state.text);
        this.setState({text: ''});
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
    publishMessage: PropTypes.func
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        publishMessage
};

export default connect(mapStateToProps, mapDispatchToProps)(Publisher);
