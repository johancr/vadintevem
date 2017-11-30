import React from 'react';
import PropTypes from 'prop-types'
import RaisedButton from 'material-ui/RaisedButton';

export default function Button(props) {

    return (
        <RaisedButton onClick={props.onClick}
            label={props.label}
         />
    );
}

Button.propTypes = {
  onClick: PropTypes.func,
  label: PropTypes.string
}
