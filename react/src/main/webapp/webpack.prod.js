const merge = require('webpack-merge');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
const common = require('./webpack.common.js');

module.exports = env => merge(common(env), {
  plugins: [
    new UglifyJSPlugin()
  ]
});
