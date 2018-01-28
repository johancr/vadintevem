import React from 'react';
import PropTypes from 'prop-types';
import Message from 'Components/Message.jsx';

export default function AnnotatedMessage(props) {

    const {message, annotate, annotation} = props;
    const content = annotate ? message.content + ' ' + annotation
                          : message.content;
    return (
            <div>
                <Message content={content} />
            </div>
        );
}

AnnotatedMessage.propTypes = {
    message: PropTypes.object,
    annotate: PropTypes.bool,
    annotation: PropTypes.string,
}
