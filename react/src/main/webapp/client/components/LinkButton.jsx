import React from 'react';
import {Route, Link} from "react-router-dom";
import Button from 'Components/Button.jsx';

export default function LinkButton({to, label, onClick}) {

    return (
        <Route exact path={to}
            children={({match}) => (
                <Link to={to}>
                    <Button label={label} disabled={match !== null} onClick={onClick}/>
                </Link>
            )}
        />
    );
};