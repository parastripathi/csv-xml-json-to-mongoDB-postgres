package com.training.fileskafkadb.utilities;

import java.io.IOException;
import java.sql.SQLException;

public class PostgreWriteThread extends Thread{


    @Override
    public void run() {

        Integer linesWritten = 0;

        PostgresFileHandler postgresFileHandler = null;

        postgresFileHandler = new PostgresFileHandler();


        while (linesWritten < 100) {
            Employee testEmployee = MyCollection.getEmployee();

            try {
                postgresFileHandler.writeEmployee(testEmployee);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            linesWritten++;
        }

        return;


    }

}
