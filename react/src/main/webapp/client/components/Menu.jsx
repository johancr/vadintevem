import React, {Component} from 'react';
import Button from 'Components/Button.jsx';
import style from 'Css/menu.css';
import Authenticator from './Authenticator.jsx';
import LinkButton from './LinkButton.jsx';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';

class Menu extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        const {authenticated} = this.props.authentication;

        return (
            <div className={style.menu} >
                <div>
                    <div className={style.menu__buttons}>
                            <LinkButton to='/' label='Read' />
                            <LinkButton to='/interact' label='Interact' />
                            <LinkButton to='/top' label='Top' />
                            <LinkButton to='/history' label='History' disabled={!authenticated} />
                            <LinkButton to='/author' label='Author' disabled={!authenticated} />
                    </div>
                    <div className={style.authenticator}>
                        <Authenticator />
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    const {authentication} = state;
    return {authentication};
}

export default withRouter(connect(mapStateToProps)(Menu));
