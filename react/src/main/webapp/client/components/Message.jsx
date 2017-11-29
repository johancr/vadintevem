import React, {Component} from 'react';
import Paper from 'material-ui/Paper';
import CircularProgress from 'material-ui/CircularProgress';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class Message extends Component {

    render() {
        const {content, isLoadingMessage} = this.props;

        return (
             <div>
                <Paper style={{paddingTop: 10,
                        paddingBottom: 10,
                        paddingLeft: 10,
                        paddingRight: 10,
                        marginTop:10,
                        marginBottom: 10,
                        width: 200,
                        height: 100}}>

                    {isLoadingMessage
                        ? <CircularProgress style={{ marginTop: 15, marginLeft: 65 }} />
                        : <p>{content}</p>
                    }
                </Paper>
             </div>
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
