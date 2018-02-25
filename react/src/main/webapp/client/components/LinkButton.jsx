import React from 'react';
import {Route, Link} from "react-router-dom";
import Button from 'Components/Button.jsx';

export default function LinkButton({to, label, onClick, disabled}) {

    const linkOnClick = e => {
        disabled ? e.preventDefault() : undefined;
    };

    return (
        <Route exact path={to}
            children={({match}) => (
                <Link to={to} onClick={linkOnClick}>
                    <Button label={label} selected={match !== null} disabled={disabled} onClick={onClick}/>
                </Link>
            )}
        />
    );
};