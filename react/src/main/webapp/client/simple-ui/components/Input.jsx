import React from 'react';

export default function Input(props) {

    return (
        <input className={props.className}
                placeholder={props.hint}
                onChange={props.onChange}
                disabled={props.disabled}
                value={props.value}/>
    );

}

