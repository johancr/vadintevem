import React, {Component} from 'react';
import Message from 'Components/Message.jsx';
import LinkButton from './LinkButton.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {setMessage} from '../actions/message.js';
import style from 'Css/interactableMessage.css';

class InteractableMessage extends Component {

    constructor(props) {
        super(props);

        this.interact = this.interact.bind(this);
    }

    interact() {
        this.props.setMessage(this.props.message);
    }

    render() {
        return (
            <div className={style.container}>
                <Message content={this.props.message.content}/>
                <LinkButton to='/interact' label='Interact' onClick={this.interact}/>
            </div>
        );
    }
}

InteractableMessage.propTypes = {
    message: PropTypes.object,
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        setMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(InteractableMessage);
