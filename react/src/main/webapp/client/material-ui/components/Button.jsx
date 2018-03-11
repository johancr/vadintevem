import React from 'react';
import PropTypes from 'prop-types'
import RaisedButton from 'material-ui/RaisedButton';

export default function Button(props) {

    return (
        <RaisedButton onClick={props.onClick}
            label={props.label}
            disabled={props.disabled}
            primary={props.selected}
            style={{minWidth: '0'}}
            labelStyle={{fontSize: '0.6em'}}
         />
    );
}

Button.propTypes = {
  onClick: PropTypes.func,
  label: PropTypes.string,
  disabled: PropTypes.bool,
  selected: PropTypes.bool,
}
