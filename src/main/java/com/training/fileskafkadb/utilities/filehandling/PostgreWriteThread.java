package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.repository.EmployeePostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgreWriteThread extends Thread{

    @Autowired
    EmployeePostgresRepository employeePostgresRepository;

    @Override
    public void run() {

        Integer linesWritten = 0;

        PostgresFileHandler postgresFileHandler = null;

        postgresFileHandler = new PostgresFileHandler();


        while (linesWritten < 150) {
            Employee testEmployee = MyCollection.getEmployee();
            employeePostgresRepository.save(testEmployee);
            //postgresFileHandler.writeEmployee(testEmployee);

            linesWritten++;
        }

        return;


    }

}
