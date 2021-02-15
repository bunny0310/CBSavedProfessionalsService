const express = require("express");
const cors = require("cors");
//npm packages - import libraries
const body_parser = require("body-parser");
const passport =require("passport");
const localStrategy =require("passport-local").Strategy;
const path = require("path");
const PORT = process.env.PORT||3000;
const session = require("express-session");
const request = require("request");
const passportJWT = require('passport-jwt');
const ExtractJWT = require('passport-jwt').ExtractJwt;
const JWTStrategy = passportJWT.Strategy;
const jwt = require('jsonwebtoken');
const router = require('./routes');

//import models
const {savedProfessional} = require("./models/savedProfessional");

// create a new express application
const app = express();

// cors configuration
const validOrigins = [
];

app.use(cors({
    origin: (origin, callback) => {
        if (validOrigins.indexOf(origin) === -1) {
            callback(null, true)
          } else {
            callback(new Error('Not allowed by CORS'))
          }
    },
    credentials: true,
    allowedHeaders: 'Content-Type,Authorization'
  }));

// Origin verification generator
app.use(session({
    secret: 'secrettexthere',
    saveUninitialized: true,      
    resave: true,
    cookie : {
    sameSite: 'none',
    secure: true
  }
}));

//set application configuration settings
app.use(body_parser.json())
app.use(express.static(path.join(__dirname,'/public')));
app.use(passport.initialize());
app.use(passport.session());
app.set('trust proxy', 1);
app.use('/api/v1/savedProfessionals', router);

// listen 
app.listen(PORT, ()=>{
    console.log("Running on port ", PORT);
});