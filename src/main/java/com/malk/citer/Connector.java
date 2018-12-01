package com.malk.citer;

import com.malk.citer.exception.Message;

import java.sql.*;

class Connector extends Message {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    //init new connector object
    Connector(String ip, String port, String dbName, String username, String password){
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            msgConnectorSuccess("database connecting success!");
        } catch (ClassNotFoundException e) {
            System.out.println("\t\t\t\tclass not found!");
            msgConnectorError("class not found!");
        } catch (SQLException e) {
            msgConnectorError("can't connect to database! please check url input or the database server might be down");
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

    //disconnect with database
    void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            msgConnectorError("database disconnecting failed!");
        }
        msgConnectorSuccess("database disconnecting success!");
    }
}
