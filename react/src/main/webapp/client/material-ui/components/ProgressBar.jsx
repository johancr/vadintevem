import React from 'react';
import LinearProgress from 'material-ui/LinearProgress';

export default function ProgressBar(props) {

    return (
        <LinearProgress mode='determinate' value={props.progress} />
    );
}
