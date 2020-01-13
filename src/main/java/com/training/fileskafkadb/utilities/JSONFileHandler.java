package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONFileHandler implements MyFileHandler {
    private static int index = 0;
    private FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter("/Users/jainilpatel/Desktop/Java Code Snippets/employeeNew.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Employee parseEmployeeObject(JSONObject employee){
        Employee employeeObject = new Employee();

        String firstName = (String) employee.get("firstName");
        employeeObject.setFirstName(firstName);

        String lastName = (String) employee.get("lastName");
        employeeObject.setLastName(lastName);

        String dateOfBirth = (String) employee.get("dateOfBirth");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateOfBirth);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        employeeObject.setDateOfBirth(date);

        Long experience = (Long) employee.get("experience");
        employeeObject.setExperience(experience);

        return employeeObject;
    }

    @Override
    public Employee readEmployee() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader("/Users/jainilpatel/Desktop/Java Code Snippets/employee.json");){
            Object obj = jsonParser.parse(fileReader);

            JSONArray employeeList = (JSONArray) obj;
            Object emp = employeeList.get(index++);
            return parseEmployeeObject((JSONObject) emp);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void writeEmployee(Employee employee) {
        JSONObject jsonObject = new JSONObject();

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy");
        String date = simpleDateFormat.format(employee.getDateOfBirth());
        Double experience = employee.getExperience();

        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("dateOfBirth",date);
        jsonObject.put("experience",experience);


        try{
            fileWriter.write(jsonObject.toJSONString()+"\n");
            fileWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        fileWriter.close();
    }
}
