const util = require('util');
const mongoose = require('mongoose');
const mysql = require('mysql');

const connection  = mysql.createPool({
  connectionLimit : 10,
  host            : 'database-1.cnx711jywpdh.us-east-2.rds.amazonaws.com',
  user            : 'root',
  password        : 'ishaan123',
  database        : 'db'
});
connection.query = util.promisify(connection.query);

const asyncQueryMethod = async (query)=>{
  try {
      const result = await connection.query(query);
      return result;
  } catch(err){
      console.log(err);
  }
}

const mongoose_connection = () => {
  mongoose.connect('mongodb+srv://ikhurana:root@cluster0.gc8z6.mongodb.net/db?retryWrites=true&w=majority', {useNewUrlParser: true, useUnifiedTopology: true});
    const db = mongoose.connection;
    db.on('error', console.error.bind(console, 'connection error:'));
    db.once('open', function() {
    // we're connected!
    // console.log('Awesome!');
    });
return mongoose;
}

module.exports = {mongoose_connection, asyncQueryMethod};