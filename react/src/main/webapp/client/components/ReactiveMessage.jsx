import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Message from 'Components/Message.jsx';
import AnnotatedMessage from 'Components/AnnotatedMessage.jsx';
import {connect} from 'react-redux';

class ReactiveMessage extends Component {

    constructor(props) {
        super(props);

        this.state = { react: false }
        this.wasThisMessageRead = this.wasThisMessageRead.bind(this);
        this.startReaction = this.startReaction.bind(this);
        this.endReaction = this.endReaction.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        const {event} = nextProps;
        if (event) {
            this.wasThisMessageRead(event);
        }
    }

    wasThisMessageRead(event) {
        if (event.messageId === this.props.message.id) {
            this.startReaction();
        }
    }

    startReaction() {
        this.setState({react: true});
        setTimeout(() => this.endReaction(), 3000);
    }

    endReaction() {
        this.setState({react: false});
    }

    render() {
        const {message, reaction} = this.props;
        const {react} = this.state;

        return (
                <AnnotatedMessage message={message} annotate={react} annotation={reaction} />
            );
    }
}

ReactiveMessage.propTypes = {
    message: PropTypes.object,
    event: PropTypes.object,
}

const mapStateToProps = state => {
    const {event} = state;
    return {event};
}

export default connect(mapStateToProps)(ReactiveMessage);
