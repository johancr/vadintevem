import React, {Component} from 'react';
import InteractableMessage from './InteractableMessage.jsx';
import {loadHistory} from '../actions/history.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import style from '../css/history.css';

class History extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const {username} = this.props.authentication;
        this.props.loadHistory(username);
    }

    render() {
        return (
            <div className={style.container}>
                {
                  this.props.history.map((message, index) =>
                    <InteractableMessage message={message} key={index} />
                  )
                }
            </div>
        );
    }
}

History.propTypes = {
    history: PropTypes.array,
    authentication: PropTypes.object,
}

const mapStateToProps = state => {
    const {history, authentication} = state;
    return {history, authentication};
}

const mapDispatchToProps = {
    loadHistory
};

export default connect(mapStateToProps, mapDispatchToProps)(History);
