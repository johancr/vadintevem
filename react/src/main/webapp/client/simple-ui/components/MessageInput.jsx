import React from 'react';
import style from '../../css/colors.css';

export default function MessageInput(props) {

    return (
        <div>
            <textarea onChange={props.onChange}
                       value={props.text}
            />
        </div>
    );
}