import React, {Component} from 'react';
import ReaderView from './ReaderView.jsx';
import InteractorView from './InteractorView.jsx';
import HistoryView from './HistoryView.jsx';
import ExplorerView from './ExplorerView.jsx';
import AuthorView from './AuthorView.jsx';
import {Route} from 'react-router-dom';
import style from '../css/viewSwitcher.css';

export default function ViewSwitcher() {

    return (
        <div className={style.container}>
            <Route exact path='/' component={ReaderView} />
            <Route path='/interact' component={InteractorView} />
            <Route path='/history' component={HistoryView} />
            <Route path='/top' component={ExplorerView} />
            <Route path='/author' component={AuthorView} />
        </div>
    );
}
