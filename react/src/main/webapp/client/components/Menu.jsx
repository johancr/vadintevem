import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar'
import Button from './Button.jsx';
import style from '../css/menu.css';
import {setView} from '../actions/view.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {READER_VIEW, INTERACTOR_VIEW} from '../constants/views.js';

class Menu extends Component {

    constructor(props) {
        super(props);

        this.setReaderView = this.setReaderView.bind(this);
        this.setInteractorView = this.setInteractorView.bind(this);
    }

    setReaderView() {
        this.props.setView(READER_VIEW);
    }

    setInteractorView() {
        this.props.setView(INTERACTOR_VIEW);
    }

    render() {
        return (
            <AppBar title='VIV'
                    showMenuIconButton={false}
                    iconElementRight={
                        <div className={style.menu__buttons}>
                            <Button label = 'Reader'
                                onClick={this.setReaderView}
                                disabled={this.props.view === READER_VIEW}
                            />
                            <Button label = 'Interactor'
                                onClick={this.setInteractorView}
                                disabled={this.props.view === INTERACTOR_VIEW}
                            />
                        </div>
                        }>
            </AppBar>
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
