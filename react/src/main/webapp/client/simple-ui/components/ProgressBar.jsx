import React from 'react';

export default function ProgressBar(props) {

    return (
        <p>{".".repeat((props.progress / 10) + 1)}</p>
    );
}
