import React, {Component} from 'react';
import SelectField from 'material-ui/SelectField';

class Selector extends Component {

    render() {
        return (<SelectField floatingLabelText={this.props.label} {...this.props} />);
    }
}

Selector.muiName = 'SelectField';

Selector.defaultProps = {
  className: 'select-field',
}

export default Selector
