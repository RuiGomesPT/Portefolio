var exports = module.exports = {};

exports.session = function () {

    global.app.use(global.session({
        secret: 'secret',
        saveUnitialized: true,
        resave: true
    }));
    
};
