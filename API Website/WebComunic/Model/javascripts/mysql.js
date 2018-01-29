var bodyParser = require("./bodyparser.js");
var cookieParser = require("./cookieParser.js");
var connection = require("./mysqlConnect.js");
var session = require("./expressSession.js");
var validator = require("./expressValidator.js");

var exports = module.exports = {};

bodyParser.bodyParser();
cookieParser.cookieParser();
connection.connection();


//funcao login

exports.logIn = function (req, res, data) {
  var username = req.body.username.toString();
  var password = req.body.password.toString();
  connection.connection();
  //var user = global.connection.escape(username);
  //var pass = global.connection.escape(password);
  global.connection.query('SELECT * FROM Utilizador WHERE Nome_Utilizador = ? LIMIT 1', [username], function (err, result) {

    if (result.length > 0) {
      if (result) {
        var object = JSON.parse(JSON.stringify(result));
        var userObject = object[0];
        var userQ = object[0].Nome_Utilizador;
        global.connection.query('SELECT Password_Utilizador from Utilizador where Nome_Utilizador = ?', [username], function (err, result) {
          console.log(result);
          if (result.length > 0) {
            if (result) {
              var object2 = JSON.parse(JSON.stringify(result));
              var passQ = object[0].Password_Utilizador;
              if (password == passQ) {
                console.log("Login efectuado com sucesso");
                console.log(userObject);
                res.cookie('IDUtil', object[0].idUtilizador);
                res.render('home', { title: 'perfil', layout: 'perfil', data: userObject });
              } else {
                console.log("1");
              }
            }
          } else if (err) {
            console.log("asdsadas");
          } else {
            console.log("2");
            res.render('home', { title: 'perfil', layout: 'registo' });
          }
        });
      }
    } else if (err) {
      console.log(err);

    } else {
      console.log("Utilizador nao encontrado");
      res.render('home', { title: 'perfil', layout: 'registo' });
    }
  });
};


//funcao logout
exports.logOut = function (req, res) {
  res.clearCookie('IDUtil');
  res.render('home', { title: 'registo', layout: 'main' });
}


//carregar perfil
exports.Perfil = function (req, res) {
  connection.connection();
  var perfilNum = req.body.perfilNum.toString();
  console.log(perfilNum);
  global.connection.query('SELECT * from Utilizador where idUtilizador = ?', [perfilNum], function (err, result) {
    console.log(result);
    if (result.length > 0) {
      if (result) {
        var object = JSON.parse(JSON.stringify(result));
        console.log(object);
        var userObject = object[0];
        console.log(userObject);
        res.render('home', { title: 'perfil', layout: 'perfil', data: userObject });
      }
    } else if (err) {
      console.log(err);
    } else {
      console.log("2");
      res.render('home', { title: 'registo', layout: 'registo' });
    }
  });
}

//Pagina de Registo
exports.Registo = function (req, res) {
  console.log("registo");
  res.render('home', { title: 'registo', layout: 'registo' });

};


//Disclaimer
exports.Disclaimer = function (req, res) {
  console.log("disclaimer");
  res.render('home', { title: 'disclaimer', layout: 'disclaimer' });

};

//Contactos
exports.Contactos = function (req, res) {
  console.log("contactos");
  res.render('home', { title: 'contactos', layout: 'contactos' });

};

//Lojas
exports.Lojas = function (req, res) {
  console.log("localizacao");
  res.render('home', { title: 'localizacao', layout: 'localizacao' });

};

//Pacote
exports.Pacote = function (req, res, data) {
  var pacote = req.body.idPacote.toString();
  console.log(pacote);
  connection.connection();
  global.connection.query('SELECT * FROM Pacotes WHERE idPacotes = ? ', [pacote], function (err, result) {
    if (result.length > 0) {
      if (result) {
        console.log(result);
        var object = JSON.parse(JSON.stringify(result));
        var packObject = object[0];
        console.log(result);
        res.render('home', { title: 'pacote', layout: 'pacote', data: packObject });
      } else if (err) {
        console.log(err);
      }
    };
  });
}

//Compra
exports.Comprar = function (req, res) {
  var utilizador = req.body.userBuy.toString();
  var produto = req.body.productBuy.toString();
  var post = { idUtilizador: utilizador, idPacote: produto };

  connection.connection();
  global.connection.query('INSERT INTO Contrato SET ?', post, function (err, rows, result) {
    if (!err) {
      console.log("success");
      res.render('home', { title: 'home', layout: 'main' });
    }
  });
};

//Importe de Pacotes
exports.getPacotes = function (req, res) {

  connection.connection();
  global.connection.query('SELECT * FROM Pacotes', function (err, rows, result) {
    if (!err) {
      var object = JSON.parse(JSON.stringify(result));
      res.send(rows);
    }
  })
};


//Registo
exports.Registar = function (req, res) {
  var username = req.body.nomeUtilizador.toString();
  var password = req.body.passUtilizador.toString();
  var email = req.body.email.toString();
  var nome = req.body.nome1.toString();
  var apelido = req.body.nome2.toString();
  var idade = req.body.idade.toString();
  var NIF = req.body.NIF.toString();
  var morada = req.body.morada.toString();
  var sexo = req.body.sexo.toString();
  var codPostal = req.body.codPostal.toString();
  connection.connection();

  var post = { Nome_Utilizador: username, Email_Utilizador: email, Password_Utilizador: password, Nome: nome, Apelido: apelido, Idade: idade, Sexo: sexo, NIF: NIF, Morada: morada, CODIGO_POSTAL: codPostal, Foto: "Utilizador.png" }

  global.connection.query('INSERT INTO Utilizador SET ?', post, function (err, rows, result) {
    if (!err) {
      res.render('home', { title: 'perfil', layout: 'main' });
    }
  });
};
