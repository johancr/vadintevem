import React from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import Publisher from './Publisher.jsx';

export default function InteractorView(props) {

    return (
        <div>
            <MessagePresenter />
            <Publisher />
        </div>
    );
}
