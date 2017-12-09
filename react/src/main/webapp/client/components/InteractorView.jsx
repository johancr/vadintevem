import React from 'react';
import MessagePresenter from './MessagePresenter.jsx';
import Publisher from './Publisher.jsx';
import style from '../css/interactor.css';

export default function InteractorView(props) {

    return (
        <div className={style.container}>
            <MessagePresenter />
            <Publisher />
        </div>
    );
}
