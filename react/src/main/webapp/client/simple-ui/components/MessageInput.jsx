import React from 'react';
import style from '../../css/colors.css';

export default function MessageInput(props) {

    return (
        <textarea style={{ width: '100%',
                           height: '100%',
                           maxWidth: '100%',
                           maxHeight: '100%',
                           border: 'none',
                           resize: 'none'  }}
                   onChange={props.onChange}
                   value={props.text}
        />
    );
}