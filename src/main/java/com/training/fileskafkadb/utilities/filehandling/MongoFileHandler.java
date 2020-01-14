package com.training.fileskafkadb.utilities.filehandling;

import com.mongodb.*;
import com.training.fileskafkadb.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class MongoFileHandler {

    private MongoClient mongo;
    DB db;

    MongoFileHandler() throws UnknownHostException {
        mongo = new MongoClient("localhost", 27017);
        db = mongo.getDB("jainildb");
    }

    private static DBObject createDBObject(EmployeeDTO employee) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("first_name", employee.getFirstName());
        docBuilder.append("last_name", employee.getLastName());
        docBuilder.append("date_of_birth", employee.getDateOfBirth());
        docBuilder.append("experience", employee.getExperience());

        return docBuilder.get();
    }


    public void writeEmployee(EmployeeDTO employee) throws UnknownHostException {

        EmployeeDTO employee1 = new EmployeeDTO();
        employee1.setFirstName("j");

        DBObject doc = createDBObject(employee1);

        DBCollection col = db.getCollection("employee");

        //create user
        WriteResult result = col.insert(doc);

        //close resources

    }

    public void finalize(){
        mongo.close();
    }

}