//npm install express
//npm install mysql
//npm install --save xml-js
const express = require('express');
const mysql = require('mysql');
const jsxmlConverter=require('xml-js');

const app = express();

//initialize mysql connection
const MYSQL_IP="localhost";
const MYSQL_LOGIN="root";
const MYSQL_PASSWORD="admin";

let con = mysql.createConnection({
  host:  MYSQL_IP,
  user: MYSQL_LOGIN,
  password: MYSQL_PASSWORD,
  database: "fast_delivery"
});

con.connect(function(err) {
  if (err){
    console.log(err);
    throw err;
  }
  console.log("Connection with mysql established");
});

//inicialize web server
app.listen(3333);

//Json
app.get('/cardapio', function(req, res){
  console.log("GET")
  const sql = "select * from cardapio";
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log("Result: ", result)
    res.status(200);
    res.setHeader('Content-Type', 'application/json')
    res.send(JSON.stringify(result))
  })
})

//XML
app.get('/cardapioXML', function(req, res){
  console.log("GET")
  const sql = "select * from cardapio";
  con.query(sql, function(err, result) {
    if (err)  throw err;
    console.log("Result: ", result)
    res.status(200);
    let options = {compact: true};
    let xml = jsxmlConverter.js2xml(result,options);
    xml=fixXML(xml);
    res.setHeader('Content-Type', 'application/xml');
    res.send(xml);
  })
})

  //define routes and its behaviors
app.get('/', function (req, res) {
  res.send('{"content": "Node JS: Express home page - First access"}');
  
})

function fixXML(xml){
  xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><objects>"+xml+"</objects>";
  i=0;
  while(xml.indexOf(i)!=-1){
    xml=xml.replace("<"+i+">", "<object>");
    xml=xml.replace("</"+i+">", "</object>");
    i++;
  }
  return xml;
}

console.log("node express is running");
