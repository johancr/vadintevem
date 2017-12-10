import React, {Component} from 'react';
import Message from './Message.jsx';
import {loadTop} from '../actions/ranking.js';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import style from '../css/explorer.css';

class ExplorerView extends Component {

    componentDidMount() {
        this.props.loadTop(10);
    }

    render() {
        return (
            <div className={style.container}>
                {
                  this.props.ranking.map((message, index) =>
                    <Message content={message.content} key={index} />
                  )
                }
            </div>
        );
    }
}

ExplorerView.propTypes = {
    ranking: PropTypes.array
}

const mapStateToProps = state => {
    const {ranking} = state;
    return {ranking};
}

const mapDispatchToProps = {
    loadTop
};

export default connect(mapStateToProps, mapDispatchToProps)(ExplorerView);
