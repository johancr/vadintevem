import React from 'react';
import PropTypes from 'prop-types'

export default function Button(props) {

    return (
        <button onClick={props.onClick}
            disabled={props.disabled}>
            {props.label}
         </button>
    );
}

Button.propTypes = {
  onClick: PropTypes.func,
  label: PropTypes.string,
  disabled: PropTypes.bool,
}
