import React, {Component} from 'react';
import Paper from 'material-ui/Paper';
import CircularProgress from 'material-ui/CircularProgress';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import style from '../../css/colors.css';

class Message extends Component {

    render() {
        const {content, isLoadingMessage} = this.props;

        return (
                <Paper style={{paddingTop: 5,
                        paddingLeft: 5,
                        paddingRight: 5,
                        paddingBottom: 5,
                        marginTop: 0,
                        width: '13em',
                        display: 'flex',
                        justifyContent: 'center',
                        flexDirection: isLoadingMessage ? 'row' : 'column',
                        textAlign: isLoadingMessage ? 'center' : 'left'}}
                        className={style.shadow}>
                    {this.props.children}
                    {isLoadingMessage
                        ? <CircularProgress size={25} thickness={2.5} />
                        : <p style={{maxWidth: '100%', maxHeight: '100%', wordWrap: 'break-word', margin: 0, overflow: 'hidden'}}>{content}</p>
                    }
                </Paper>
         );
    }
}

Message.propTypes = {
    isLoadingMessage: PropTypes.bool,
}

const mapStateToProps = state => {
    const {isLoadingMessage} = state;
    return {isLoadingMessage};
}

export default connect(mapStateToProps)(Message);
