import React from 'react';
import Message from './Message.jsx';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

function MessagePresenter(props) {

    return (
        <div>
            <Message content={props.message.content}/>
        </div>
    );
}

MessagePresenter.propTypes = {
    message: PropTypes.object
}

const mapStateToProps = state => {
    const {message} = state;
    return {message};
}

export default connect(mapStateToProps)(MessagePresenter);
