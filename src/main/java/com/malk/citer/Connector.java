package com.malk.citer;

import com.malk.citer.exception.Message;

import java.sql.*;

class Connector extends Message {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    private String ip;
    private String port;
    private String username;
    private String password;
    private String dbName;

    //init new connector object
    Connector(String ip, String port, String dbName, String username, String password){
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            this.ip = ip;
            this.port = port;
            this.username = username;
            this.password = password;
            this.dbName = dbName;
            msgConnectorSuccess("database connecting success!");
        } catch (ClassNotFoundException e) {
            msgConnectorError("class not found!");
        } catch (SQLException e) {
            msgConnectorError("can't connect to database! please check url input or the database server might be down");
        }
    }

    Connector(String ip, String port, String username, String password){
        String url = "jdbc:mysql://"+ip+":"+port;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            this.ip = ip;
            this.port = port;
            this.username = username;
            this.password = password;
            msgConnectorSuccess("server connecting success!");
        } catch (ClassNotFoundException e) {
            msgConnectorError("class not found!");
        } catch (SQLException e) {
            msgConnectorError("can't connect to server! please check url input or the server might be down");
        }
    }

    //connection object from MySQL to Gayo class
    Connection getConnection() {
        return connection;
    }

    //execute query
    void executeQuery(String query){
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            msgConnectorSuccess("successfully executing query "+query+" !");
            closeResult();
        } catch (Exception e) {
            msgConnectorError("error while executing "+query+" !");
        }
    }

    //execute command
    void execute(String command){
        try {
            statement = connection.createStatement();
            statement.execute(command);
            msgConnectorSuccess("successfully executing command "+command+" !");
        } catch (SQLException e) {
            msgConnectorError("error while executing "+command+" !");
        }
    }

    //close statement after being used from other method
    void closeStatement(){
        try {
            statement.close();
        } catch (SQLException e) {
            msgConnectorError("error while closing statement!");
        }
        msgConnectorSuccess("closing statement successfully!");
        statement = null;
    }

    //get result object after execute query
    ResultSet getResult(){
        return result;
    }

    //create new database
    void createNewDatabase(String name){
        try{
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE "+name);
            statement.close();
            this.dbName = name;
            reConnect();
            msgConnectorSuccess("successfully create database "+name+" !");
        } catch (SQLException e){
            msgConnectorError("error while create new database!");
        }
    }

    void dropDatabase(String name){
        try{
            statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE "+name);
            statement.close();
            this.dbName = "<NuLL>";
            msgConnectorSuccess("successfully drop database "+name+" !");
        } catch (SQLException e){
            msgConnectorError("error while drop database!");
        }
    }

    //create new table [ERROR!]
    void createNewTable(String name){
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE "+name);
            statement.close();
            msgConnectorSuccess("successfully create table "+name+" !");
        } catch (SQLException e) {
            msgConnectorError("error while create new table!");
        }
    }

    void dropTable(String name){
        try{
            statement = connection.createStatement();
            statement.execute("DROP TABLE "+name);
            msgConnectorSuccess("successfully drop table "+name+" !");
        } catch (SQLException e){
            msgConnectorError("error while drop table!");
        }
    }

    private void getResultFromTable(ResultSet result){

    }

    //close result and set to null so it can be used again
    private void closeResult(){
        try {
            result.close();
        } catch (SQLException e) {
            msgConnectorError("error while closing result!");
        }
        msgConnectorSuccess("successfully closing result!");
        result = null;
    }

    //connect to new created database
    private void reConnect(){
        closeConnection();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName;
        try {
            connection = DriverManager.getConnection(url,username,password);
            msgConnectorSuccess("redirect to database "+dbName+" success!");
            msgConnectorSuccess("database connecting success!");
        }catch (SQLException e) {
            msgConnectorError("can't connect to database! please check url input or the database server might be down");
        }
    }

    //disconnect with database
    void closeConnection(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            msgConnectorError("database disconnecting failed!");
        }
        msgConnectorSuccess("database disconnecting success!");
    }
}
