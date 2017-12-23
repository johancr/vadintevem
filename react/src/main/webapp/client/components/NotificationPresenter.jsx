import React, {Component} from 'react';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';
import style from 'Css/notification.css';
import {reset} from '../actions/notification.js';

class NotificationPresenter extends Component {

    constructor(props) {
        super(props);
    }

    componentDidUpdate() {
        setTimeout(() => this.props.reset(), 3000);
    }

    render() {
        return (
                <div className={style.container}>
                    {this.props.notification}
                </div>
            );
    }
}

NotificationPresenter.PropTypes = {
    notification: PropTypes.object,
    reset: PropTypes.func,
}

const mapStateToProps = state => {
    const {notification} = state;
    return {notification};
}

const mapDispatchToProps = {
    reset,
}

export default connect(mapStateToProps, mapDispatchToProps)(NotificationPresenter);