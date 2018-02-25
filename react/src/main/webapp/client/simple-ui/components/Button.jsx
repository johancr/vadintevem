import React from 'react';
import PropTypes from 'prop-types'

export default function Button(props) {

    const style = {
        fontWeight: props.selected ? 'bolder' : 'normal',
    };

    return (
        <button style={style} onClick={props.onClick}
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
