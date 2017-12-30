import React, {Component} from 'react';
import Message from 'Components/Message.jsx';
import Button from 'Components/Button.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {INTERACTOR_VIEW} from '../constants/views.js';
import {setView} from '../actions/view.js';
import {setMessage} from '../actions/message.js';
import style from '../css/interactableMessage.css';

class InteractableMessage extends Component {

    constructor(props) {
        super(props);

        this.interact = this.interact.bind(this);
    }

    interact() {
        this.props.setMessage(this.props.message);
        this.props.setView(INTERACTOR_VIEW);
    }

    render() {
        return (
            <div className={style.container}>
                <Message content={this.props.message.content}/>
                <Button onClick={this.interact} label = 'Interact' />
            </div>
        );
    }
}

InteractableMessage.propTypes = {
    message: PropTypes.object,
    setView: PropTypes.func
}

const mapStateToProps = state => {
    return {};
}

const mapDispatchToProps = {
        setView,
        setMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(InteractableMessage);
