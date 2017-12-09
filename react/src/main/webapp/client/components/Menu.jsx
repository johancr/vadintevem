import React, {Component} from 'react';
import Button from './Button.jsx';
import style from '../css/menu.css';
import {setView} from '../actions/view.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {READER_VIEW, INTERACTOR_VIEW, HISTORY_VIEW} from '../constants/views.js';

class Menu extends Component {

    constructor(props) {
        super(props);

        this.setReaderView = this.setReaderView.bind(this);
        this.setInteractorView = this.setInteractorView.bind(this);
        this.setHistoryView = this.setHistoryView.bind(this);
    }

    setReaderView() {
        this.props.setView(READER_VIEW);
    }

    setInteractorView() {
        this.props.setView(INTERACTOR_VIEW);
    }

    setHistoryView() {
        this.props.setView(HISTORY_VIEW);
    }

    render() {
        return (
            <div className={style.menu} >
                <span className={style.menu__header}>VIV</span>
                <div className={style.menu__buttons}>
                        <Button label = 'Read'
                            onClick={this.setReaderView}
                            disabled={this.props.view === READER_VIEW}
                        />
                        <Button label = 'Interact'
                            onClick={this.setInteractorView}
                            disabled={this.props.view === INTERACTOR_VIEW}
                        />
                        <Button label = 'History'
                            onClick={this.setHistoryView}
                            disabled={this.props.view === HISTORY_VIEW}
                        />
                </div>
            </div>
        );
    }
}

Menu.propTypes = {
    setView: PropTypes.func
}

const mapStateToProps = state => {
    const {view} = state;
    return {view};
}

const mapDispatchToProps = {
        setView
};

export default connect(mapStateToProps, mapDispatchToProps)(Menu);
