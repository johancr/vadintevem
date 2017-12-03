import React, {Component} from 'react';
import ReaderView from './ReaderView.jsx';
import InteractorView from './InteractorView.jsx';
import {READER_VIEW} from '../constants/views.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class ViewSwitcher extends Component {

    constructor(props) {
        super(props);

        this.renderView = this.renderView.bind(this);
    }

    renderView() {
        return (
            this.props.view === READER_VIEW
                ? <ReaderView />
                : <InteractorView />
        );
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