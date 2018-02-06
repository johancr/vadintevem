import React from 'react';
import style from '../../css/colors.css';
import TextArea from 'react-textarea-autosize';

export default function MessageInput(props) {

    return (
        <div>
            <TextArea onChange={props.onChange}
                       value={props.text}
            />
        </div>
    );
}