import React, {Component} from 'react';
import ReaderView from './ReaderView.jsx';
import InteractorView from './InteractorView.jsx';
import {READER_VIEW, INTERACTOR_VIEW, HISTORY_VIEW} from '../constants/views.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import HistoryView from './HistoryView.jsx';

class ViewSwitcher extends Component {

    constructor(props) {
        super(props);

        this.renderView = this.renderView.bind(this);
    }

    renderView() {
        switch(this.props.view) {
            case READER_VIEW:
                return <ReaderView />
            case INTERACTOR_VIEW:
                return <InteractorView />
            case HISTORY_VIEW:
                return <HistoryView />
        }
    }

    render() {
        return this.renderView();
    }
}

ViewSwitcher.propTypes = {
    view: PropTypes.string
}

const mapStateToProps = state => {
    const {view} = state;
    return {view};
}

export default connect(mapStateToProps)(ViewSwitcher);