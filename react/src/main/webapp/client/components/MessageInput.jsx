import React from 'react';
import Paper from 'material-ui/Paper';
import style from '../css/colors.css';

export default function MessageInput(props) {

    return (
        <Paper style={{paddingTop: 5,
                        paddingBottom: 10,
                        paddingLeft: 5,
                        paddingRight: 10,
                        marginBottom: 10,
                        width: 200,
                        height: 100}}
               className={style.shadow}
         >
            <textarea style={{ width: '100%',
                               height: '100%',
                               maxWidth: '100%',
                               maxHeight: '100%',
                               border: 'none',
                               resize: 'none'  }}
                       onChange={props.onChange}
                       value={props.text}
            />
        </Paper>
    );
}