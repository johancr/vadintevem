import React, {Component} from 'react';
import MenuItem from 'material-ui/MenuItem';

class SelectorItem extends Component {

    render() {
        return (<MenuItem primaryText={this.props.label} {...this.props} />);
    }
}

SelectorItem.muiName = 'MenuItem';

SelectorItem.defaultProps = {
  className: 'menu-item',
}

export default SelectorItem