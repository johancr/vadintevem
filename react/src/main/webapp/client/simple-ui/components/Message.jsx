import React, {Component} from 'react';

class Message extends Component {

    render() {
        const {content} = this.props;

        return (
            <p style={{wordWrap: 'break-word'}}>{content}</p>
         );
    }
}

export default Message;
