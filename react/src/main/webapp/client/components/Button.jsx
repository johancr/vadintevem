import React from 'react';
import PropTypes from 'prop-types'

export default function Button(props) {

    return (
        <button onClick = {props.onClick}>{props.title}</button>
    );

}

Button.propTypes = {
  onClick: PropTypes.func,
  title: PropTypes.string
}
