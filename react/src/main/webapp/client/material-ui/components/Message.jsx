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
                <Paper style={{paddingTop: 10,
                        paddingBottom: 10,
                        paddingLeft: 10,
                        paddingRight: 10,
                        marginTop:10,
                        marginBottom: 10,
                        width: 200,
                        height: 100,
                        overflow: 'scroll'}}
                        className={style.shadow}>

                    {isLoadingMessage
                        ? <CircularProgress style={{ marginTop: 15, marginLeft: 65 }} />
                        : <p style={{maxWidth: '100%', wordWrap: 'break-word'}}>{content}</p>
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
