package com.malk.citer.database;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private String dbName;
    private List<Attribute> attributes;

    Database(String dbName, Attribute...attributes){
        this.attributes = new ArrayList<Attribute>();
    }

}
