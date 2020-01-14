package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private static List<Employee> list = new ArrayList<>();

    public synchronized static void add(Employee employee){
        list.add(employee);
    }

    public static List<Employee> getList(){
        return list;
    }
}
