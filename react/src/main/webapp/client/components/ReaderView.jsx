import React from 'react';
import MessageFeed from './MessageFeed.jsx';
import AlgorithmSelector from './AlgorithmSelector.jsx';
import style from '../css/reader.css';

export default function ReaderView(props) {

    return (
        <div className={style.container}>
            <MessageFeed />
            <AlgorithmSelector />
        </div>
    );
}
