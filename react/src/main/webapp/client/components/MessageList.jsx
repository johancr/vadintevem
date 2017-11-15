import React from 'react';
import Message from './Message.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

function MessageList(props) {

    return (
        <div>
            {props.messages.map((message, index) => {
                return <Message key={index} content={message.content}/>
              })}
        </div>
    );
}

MessageList.propTypes = {
    messages: PropTypes.array
}

const mapStateToProps = state => {
    const {messages} = state;
    return {messages};
}

export default connect(mapStateToProps)(MessageList);
