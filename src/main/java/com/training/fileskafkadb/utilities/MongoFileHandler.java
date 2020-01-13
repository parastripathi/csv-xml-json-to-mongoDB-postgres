package com.training.fileskafkadb.utilities;

import com.mongodb.*;
import com.training.fileskafkadb.dta.Employee;

import java.net.UnknownHostException;

public class MongoFileHandler {

    private MongoClient mongo;
    DB db;

    MongoFileHandler() throws UnknownHostException {
        mongo = new MongoClient("localhost", 27017);
        db = mongo.getDB("trainingdb");
    }

    private static DBObject createDBObject(Employee employee) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("first_name", employee.getFirstName());
        docBuilder.append("last_name", employee.getLastName());
        docBuilder.append("date_of_birth", employee.getDateOfBirth());
        docBuilder.append("experience", employee.getExperience());

        return docBuilder.get();
    }


    public void writeEmployee(Employee employee) throws UnknownHostException {

        DBObject doc = createDBObject(employee);



        DBCollection col = db.getCollection("employee");

        //create user
        WriteResult result = col.insert(doc);

        //close resources

    }

    public void finalize(){
        mongo.close();
    }

}