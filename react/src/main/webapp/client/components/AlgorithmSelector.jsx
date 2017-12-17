import React, {Component} from 'react';
import Selector from './Selector.jsx';
import SelectorItem from './SelectorItem.jsx';

class AlgorithmSelector extends Component {

    constructor(props) {
        super(props);

        this.state = { value: 1 };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event, index, value) {
        this.setState({ value });
    }

    render() {
        return (
            <Selector label='Algorithm'
                      value={this.state.value}
                      onChange={this.handleChange} >
                <SelectorItem value={1} label='Random' />
                <SelectorItem value={2} label='Popular' />
            </Selector>
        );
    }
}

export default AlgorithmSelector;