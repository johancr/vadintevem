import React from 'react';
import Paper from 'material-ui/Paper';
import style from '../../css/colors.css';
import TextArea from 'react-textarea-autosize';

export default function MessageInput(props) {

    return (
        <Paper style={{paddingTop: 0,
                        paddingBottom: 0,
                        paddingLeft: 0,
                        paddingRight: 5,
                        width: 200,
                        minHeight: 36 }}
               className={style.shadow}
         >
            <TextArea style={{ width: '100%',
                               minHeight: '100%',
                               outline: 'none',
                               border: 'none',
                               resize: 'none' }}
                       onChange={props.onChange}
                       value={props.text}
            />
        </Paper>
    );
}