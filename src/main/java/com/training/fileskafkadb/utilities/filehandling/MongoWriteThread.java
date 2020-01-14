package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.dto.EmployeeDTO;
import com.training.fileskafkadb.repository.EmployeeMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class MongoWriteThread extends Thread {

    @Autowired
    EmployeeMongoRepository employeeRepository;

    private EmployeeDTO testEmployee;
    private List<EmployeeDTO> employees;

    @Override
    public void run() {

        Integer linesWritten = 0;

        MongoFileHandler mongoFileHandler = null;

        try {
            mongoFileHandler = new MongoFileHandler();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        while (linesWritten < 150) {

            Employee testEmployee = MyCollection.getEmployee();

            employeeRepository.save(testEmployee);
            //mongoFileHandler.writeEmployee(testEmployee);

            linesWritten++;
        }

        return;


    }

}
