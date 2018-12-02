package com.malk.citer;

import com.malk.citer.exception.Message;

public class Citer extends Message {

    private Connector connector;

    private String dbName;

    //init new database connection
    public Citer(String ip, String port, String dbName, String username, String password){
        msgMe();
        msgConnect("jdbc:mysql://"+ip+":"+port+"/"+dbName);
        connector = new Connector(ip, port, dbName, username, password);
        this.dbName = dbName;
    }

    //init new connection with no database
    public Citer(String ip, String port, String username, String password){
        msgMe();
        msgConnect("jdbc:mysql://"+ip+":"+port);
        connector = new Connector(ip,port,username,password);
    }

    //create new database
    public void createDatabase(String dbName){
        msgExecute("CREATE DATABASE "+dbName);
        connector.createNewDatabase(dbName);
        this.dbName = dbName;
    }

    public void deleteDatabase(String dbName){
        msgExecute("DROP DATABASE "+dbName);
        connector.dropDatabase(dbName);
    }

    //use manual query
    public void manualExecuteQuery(String query){
        msgExecute(query);
        connector.executeQuery(query);
    }

    //use manual command
    public void manualExecute(String command){
        msgExecute(command);
        connector.execute(command);
    }

    //create new database table [ERROR!]
    public void createTable(String name){
        msgExecute("CREATE TABLE "+name);
        connector.createNewTable(name);
    }

    //delete existing table
    public void deleteTable(String name){
        msgExecute("DROP TABLE "+name);
        connector.dropTable(name);
    }

    //close database connection
    public void close(){
        msgClose(dbName);
        connector.closeConnection();
    }

}
