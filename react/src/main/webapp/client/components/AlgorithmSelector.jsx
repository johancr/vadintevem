import React, {Component} from 'react';
import Selector from './Selector.jsx';
import SelectorItem from './SelectorItem.jsx';
import {RANDOM, POPULAR, UNREAD} from '../constants/algorithms.js';
import {setAlgorithm} from '../actions/algorithm.js';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';

class AlgorithmSelector extends Component {

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event, index, value) {
        this.props.setAlgorithm(value);
    }

    render() {
        return (
            <Selector label='Algorithm'
                      value={this.props.algorithm}
                      onChange={this.handleChange}
                      style={{width: 200}} >
                <SelectorItem value={RANDOM} label='Random' />
                <SelectorItem value={POPULAR} label='Popular' />
                <SelectorItem value={UNREAD} label='Unread' />
            </Selector>
        );
    }
}

AlgorithmSelector.propTypes = {
    setAlgorithm: PropTypes.func,
    algorithm: PropTypes.string,
}

const mapStateToProps = state => {
    const {algorithm} = state;
    return {algorithm};
}

const mapDispatchToProps = {
        setAlgorithm,
};

export default connect(mapStateToProps, mapDispatchToProps)(AlgorithmSelector);