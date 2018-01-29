var cookieParser = require('cookie-parser')
var exports = module.exports = {};

exports.cookieParser = function () {
    global.app.set('trust proxy', 1)
    global.app.use(cookieParser());
}