const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const webpack = require('webpack');

module.exports = env => merge(common(env), {

    devtool: 'inline-source-map',
    devServer: {
        port: 9000,
        hot: true,
    },

    plugins: [
        new webpack.NamedModulesPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ],
});
