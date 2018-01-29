var exports = module.exports = {};

exports.connection = function () {

    global.connection = mysql.createConnection({
        host: 'webitcloud.net',
        user: 'webitclo_G508',
        password: '3XDowl6TH3Ai',
        database: 'webitclo_G508'
    });
    global.connection.connect();
};


