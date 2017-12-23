import React, {Component} from 'react';

class Selector extends Component {

    render() {
        return (<select>
                    {this.props.children}
                </select>);
    }
}

export default Selector
