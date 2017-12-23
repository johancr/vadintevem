import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class Message extends Component {

    render() {
        const {content, isLoadingMessage} = this.props;

        return (
            <div>
                {isLoadingMessage
                    ? <p>Loading...</p>
                    : <p style={{maxWidth: '100%', wordWrap: 'break-word'}}>{content}</p>}
            </div>
         );
    }
}

Message.propTypes = {
    isLoadingMessage: PropTypes.bool,
}

const mapStateToProps = state => {
    const {isLoadingMessage} = state;
    return {isLoadingMessage};
}

export default connect(mapStateToProps)(Message);
