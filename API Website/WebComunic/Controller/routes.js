var mysql = require("../Model/javascripts/mysql.js");

var exports = module.exports = {};

exports.init = function () {

  app.engine('handlebars', exphbs({ defaultLayout: 'main' }));
  app.set('view engine', 'handlebars');
  app.get('/', function (req, res) {
    res.render('home');
  })

  global.app.post('/login', function (req, res) {
    mysql.logIn(req, res);
  });

  global.app.post('/getPacotes', function (req, res) {
    mysql.getPacotes(req, res);
  });

  global.app.post('/registo', function (req, res) {
    mysql.Registo(req, res);
  });

  global.app.post('/registar', function (req, res) {
    mysql.Registar(req, res); 
  });

  global.app.post('/pacote', function (req, res) {
    mysql.Pacote(req, res); 
  });

  global.app.post('/comprar', function (req, res) {
    mysql.Comprar(req, res); 
  });

  global.app.post('/logOut', function (req, res) {
    mysql.logOut(req, res); 
  });

  global.app.post('/perfil', function (req, res) {
    mysql.Perfil(req, res); 
  });

  global.app.post('/disclaimer', function (req, res) {
    mysql.Disclaimer(req, res); 
  });

  global.app.post('/contactos', function (req, res) {
    mysql.Contactos(req, res); 
  });

  global.app.post('/lojas', function (req, res) {
    mysql.Lojas(req, res); 
  });
  
};

