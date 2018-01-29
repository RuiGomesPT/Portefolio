http = require('http');
express = require('express');
exphbs = require('express-handlebars');
session = require('express-session');
//flash = require('connect-flash');
path = require('path');
mysql = require('mysql');
bodyParser = require('body-parser');
cookieSession = require('cookie-session');
assert = require('assert');
passport = require('passport');
LocalStragety = require('passport-local').Strategy;

app = express();

//app.use(flash());

app.use(passport.initialize());
app.use(passport.session());

// app.use(function(req, res, next) {
//   res.locals.success_msg = req.flash('success_msg');
//   res.locals.error_msg = req.flash('error_msg');
//   res.locals.error = req.flash('error');
// });

var start = require("./Controller/routes.js")

start.init();

app.listen(process.env.PORT || 3000, function () {
  console.log("Express server listening on port %d in %s mode", this.address().port, app.settings.env);
});

