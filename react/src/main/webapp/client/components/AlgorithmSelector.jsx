import React, {Component} from 'react';
import Selector from 'Components/Selector.jsx';
import SelectorItem from 'Components/SelectorItem.jsx';
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
        const {authenticated} = this.props.authentication;

        return (
            <Selector label='Algorithm'
                      value={this.props.algorithm}
                      onChange={this.handleChange}
                      style={{width: 200}} >
                <SelectorItem value={RANDOM} label='Random' />
                <SelectorItem value={POPULAR} label='Popular' />
                <SelectorItem value={UNREAD} label='Unread' disabled={!authenticated} />
            </Selector>
        );
    }
}

AlgorithmSelector.propTypes = {
    setAlgorithm: PropTypes.func,
    algorithm: PropTypes.string,
}

const mapStateToProps = state => {
    const {algorithm, authentication} = state;
    return {algorithm, authentication};
}

const mapDispatchToProps = {
        setAlgorithm,
};

export default connect(mapStateToProps, mapDispatchToProps)(AlgorithmSelector);