package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;

public class MyCollection {

    private static Employee[] employeeList = new Employee[300];
    private static Integer writeCounter = 0;
    private static Integer readCounter = 0;

    public MyCollection() {

        for(Integer indexCounter = 0 ; indexCounter < 300 ; indexCounter++){
            employeeList[indexCounter] = new Employee();
        }
    }

    static synchronized void addEmployee(Employee newEmployee) {

        if (employeeList[writeCounter] == null) employeeList[writeCounter] = new Employee();

        employeeList[writeCounter].setFirstName(newEmployee.getFirstName());
        employeeList[writeCounter].setLastName(newEmployee.getLastName());
        employeeList[writeCounter].setDateOfBirth(newEmployee.getDateOfBirth());
        employeeList[writeCounter].setExperience(newEmployee.getExperience());

       // System.out.println(employeeList[writeCounter]);


        writeCounter++;
    }

    public static Integer getWriteCounter() {
        return writeCounter;
    }

    public static Integer getReadCounter() {
        return readCounter;
    }

    static synchronized Employee getEmployee() {
        //System.out.println("Retrieved" + employeeList[readCounter]);
        return employeeList[readCounter++];
    }

    static Employee[] getEmployeeList(){
        return employeeList;
    }


}
