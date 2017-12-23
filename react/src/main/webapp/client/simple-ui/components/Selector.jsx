import React, {Component} from 'react';

class Selector extends Component {

    constructor(props) {
        super(props)
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        this.props.onChange(undefined, undefined, event.target.value);
    }

    render() {
        return (<select onChange={this.onChange} >
                    {this.props.children}
                </select>);
    }
}

export default Selector
