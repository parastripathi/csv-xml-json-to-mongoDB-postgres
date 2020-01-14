package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.service.WriteService;
import com.training.fileskafkadb.utilities.filehandling.MongoWriteThread;
import com.training.fileskafkadb.utilities.filehandling.MyCollection;
import com.training.fileskafkadb.utilities.filehandling.PostgreWriteThread;
import org.springframework.stereotype.Service;

@Service
public class WriteServiceImpl implements WriteService {

    @Override
    public void writeAllFiles() {

        KafkaConsumerServiceImpl kafkaConsumerService = new KafkaConsumerServiceImpl();
        kafkaConsumerService.startConsumer();

        MongoWriteThread mongoWriteThread = new MongoWriteThread();
        mongoWriteThread.start();

        PostgreWriteThread postgreWriteThread = new PostgreWriteThread();
        postgreWriteThread.start();

        try {
            mongoWriteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            postgreWriteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(MyCollection.getReadCounter());
        System.out.println(MyCollection.getWriteCounter());
    }
}
