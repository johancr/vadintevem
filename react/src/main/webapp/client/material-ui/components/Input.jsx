import React from 'react';
import TextField from 'material-ui/TextField';

export default function Input(props) {

    return (
        <TextField className={props.className}
                    hintText={props.hint}
                    onChange={props.onChange}
                    value={props.value}
                    disabled={props.disabled}
                    inputStyle={props.style}/>
    );
}

