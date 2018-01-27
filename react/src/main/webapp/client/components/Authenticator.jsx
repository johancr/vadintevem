import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import Input from 'Components/Input.jsx';
import Button from 'Components/Button.jsx';
import style from 'Css/authenticator.css';
import {logIn, logOut} from '../actions/authentication.js';
import {listenForEvents} from '../actions/events.js';

class Authenticator extends Component {

    constructor(props) {
        super(props);
        this.state = {username : ''};

        this.onChange = this.onChange.bind(this);
        this.onLogIn = this.onLogIn.bind(this);
        this.onLogOut = this.onLogOut.bind(this);
    }

    onChange(event) {
        this.setState({username: event.target.value});
    }

    onLogIn() {
        const {username} = this.state;
        this.props.logIn(username);
        this.props.listenForEvents(username);
    }

    onLogOut() {
        this.props.logOut();
    }

    render() {
        const {authenticated, username} = this.props.authentication;
        const {onChange, onLogIn, onLogOut} = this;

        switch(authenticated) {
            case false:
                return (
                    <div>
                        <Input className={style.input} hint='Username' value={this.state.username} onChange={onChange} style={{textAlign: 'center'}}/>
                        <Button onClick={onLogIn} label='Log in'/>
                    </div>
                )
             case true:
                return (
                    <div>
                        <Input className={style.input} disabled={true} value={username} style={{textAlign: 'center'}}/>
                        <Button onClick={onLogOut} label='Log out'/>
                    </div>
                )
        }
    }
}

Authenticator.propTypes = {
    logIn: PropTypes.func,
    logOut: PropTypes.func,
    listenForEvents: PropTypes.func,
}

const mapStateToProps = state => {
    const {authentication} = state;
    return {authentication};
}

const mapDispatchToProps = {
    logIn,
    logOut,
    listenForEvents,
};

export default connect(mapStateToProps, mapDispatchToProps)(Authenticator);
