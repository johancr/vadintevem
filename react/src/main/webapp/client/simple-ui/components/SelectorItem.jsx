import React, {Component} from 'react';

class SelectorItem extends Component {

    render() {
        return (
                <option value={this.props.value} disabled={this.props.disabled}>{this.props.label}</option>
        );
    }
}

export default SelectorItem