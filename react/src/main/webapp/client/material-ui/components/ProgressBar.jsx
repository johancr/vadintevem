import React from 'react';
import LinearProgress from 'material-ui/LinearProgress';

export default function ProgressBar(props) {

    return (
        <LinearProgress style={{marginTop: '10px', width: '200px'}} mode='determinate' value={props.progress} />
    );
}
