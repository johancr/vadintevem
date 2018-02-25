import React from 'react';
import Button from 'Components/Button.jsx';
import style from 'Css/menu.css';
import Authenticator from './Authenticator.jsx';
import LinkButton from './LinkButton.jsx';

export default function Menu(props){

    return (
        <div className={style.menu} >

            <div>
                <div className={style.authenticator}>
                    <Authenticator />
                </div>
                <div className={style.menu__buttons}>
                        <LinkButton to='/' label='Read' />
                        <LinkButton to='/interact' label='Interact' />
                        <LinkButton to='/history' label='History' />
                        <LinkButton to='/top' label='Top' />
                        <LinkButton to='/author' label='Author' />
                </div>
            </div>
        </div>
    );
}
