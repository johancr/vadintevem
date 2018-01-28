import React from 'react';
import PropTypes from 'prop-types';
import Message from 'Components/Message.jsx';
import style from 'Css/reactiveMessage.css';

export default function AnnotatedMessage(props) {

    const {message, annotate, annotation} = props;
    return (
            <div>
                <Message content={message.content}>
                    { annotate ? <span className={style.annotation}>{annotation}</span> : undefined}
                </Message>
            </div>
        );
}

AnnotatedMessage.propTypes = {
    message: PropTypes.object,
    annotate: PropTypes.bool,
    annotation: PropTypes.string,
}
