import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {loadAuthored} from '../actions/author.js';
import ReactiveMessage from './ReactiveMessage.jsx';

const messageRead = '↗';

class AuthorView extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const {username} = this.props.authentication;
        this.props.loadAuthored(username);
    }

    render() {
        const {authored} = this.props;
        return (
            <div>
                {
                  authored.map((message, index) =>
                    <ReactiveMessage message={message} reaction={messageRead} key={index} />
                  )
                }
            </div>
        );
    }
}

AuthorView.propTypes = {
    authored: PropTypes.array,
    authentication: PropTypes.object,
}

const mapStateToProps = state => {
    const {authored, authentication} = state;
    return {authored, authentication};
}

const mapDispatchToProps = {
    loadAuthored
};

export default connect(mapStateToProps, mapDispatchToProps)(AuthorView);

