import React from 'react';
import Paper from 'material-ui/Paper';

export default function Message(props) {
    return (
         <div>
            <Paper style={{paddingTop: 10, 
                    paddingBottom: 10, 
                    paddingLeft: 10, 
                    paddingRight: 10, 
                    marginTop:10, 
                    marginBottom: 10, 
                    width: 200, 
                    height: 100}}>
                <p>{props.content}</p>
            </Paper>
         </div>
     );
}
