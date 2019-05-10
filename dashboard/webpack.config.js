var config = require('./build/WebPackHelper.js');
var path = require('path');
const TerserPlugin = require('./build/node_modules/terser-webpack-plugin');

module.exports = {
  entry: config.moduleName,
  optimization: {
      minimizer: [new TerserPlugin()],
  },
  output: {
    path: path.resolve('./bundle'),
    publicPath: '/build/',
    filename: 'bundle.js'
  },
  resolve: {
    modules: [ path.resolve('js'), path.resolve('..', 'src'), path.resolve('.'), path.resolve('node_modules') ],
    extensions: ['.js', '.css']
  },
  module: {
    rules: [
      { test: /\.css$/, use: [ 'style-loader', 'css-loader' ] }
    ]
  },
  devtool: '#source-map',
  plugins: [
  ]
};

console.log(module.exports.resolve.modules);


