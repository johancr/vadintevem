import React, {Component} from 'react';
import Message from './Message.jsx';
import {loadHistory} from '../actions/history.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class History extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.loadHistory();
    }

    render() {
        return (
            <div>
                {
                  this.props.history.map((message, index) =>
                    <Message content={message.content} key={index} />
                  )
                }
            </div>
        );
    }
}

History.propTypes = {
    history: PropTypes.array
}

const mapStateToProps = state => {
    const {history} = state;
    return {history};
}

const mapDispatchToProps = {
    loadHistory
};

export default connect(mapStateToProps, mapDispatchToProps)(History);
