package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.service.impl.KafkaConsumerServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyCollection {

    private static Employee[] employeeList = new Employee[300];
    private static Integer writeCounter = 0;
    private static Integer readCounter = 0;
    private static KafkaConsumerServiceImpl kafkaConsumerService = new KafkaConsumerServiceImpl();
    private static List<Employee> kafkaConsumerList = new ArrayList<Employee>();

    public static List<Employee> getKafkaConsumerList() {
        return kafkaConsumerList;
    }

    public static void setKafkaConsumerList(List<Employee> kafkaConsumerList) {
        MyCollection.kafkaConsumerList = kafkaConsumerList;
    }

    public static void setEmployeeList(Employee[] employeeList) {
        MyCollection.employeeList = employeeList;
    }

    public MyCollection() {

        for(Integer indexCounter = 0 ; indexCounter < 300 ; indexCounter++){
            employeeList[indexCounter] = new Employee();
        }
    }

    public static synchronized void addEmployee(Employee newEmployee) {

        if (employeeList[writeCounter] == null) employeeList[writeCounter] = new Employee();
        employeeList[writeCounter].setFirstName(newEmployee.getFirstName());
        employeeList[writeCounter].setLastName(newEmployee.getLastName());
        employeeList[writeCounter].setDateOfBirth(newEmployee.getDateOfBirth());
        employeeList[writeCounter].setExperience(newEmployee.getExperience());
        writeCounter++;
    }

    public static Integer getWriteCounter() {
        return writeCounter;
    }

    public static Integer getReadCounter() {
        return readCounter;
    }

    static synchronized Employee getEmployee() {
        //System.out.println(readCounter);
        //return kafkaConsumerList.get(readCounter++);
        return null;
    }

    public static Employee[] getEmployeeList(){
        return employeeList;
    }


}
