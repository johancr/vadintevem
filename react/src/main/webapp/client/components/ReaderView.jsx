import React from 'react';
import MessageFeed from './MessageFeed.jsx';
import AlgorithmSelector from './AlgorithmSelector.jsx';

export default function ReaderView(props) {

    return (
        <div>
            <MessageFeed />
            <AlgorithmSelector />
        </div>
    );
}
