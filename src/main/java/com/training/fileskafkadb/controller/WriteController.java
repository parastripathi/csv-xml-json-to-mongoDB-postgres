package com.training.fileskafkadb.controller;

import com.training.fileskafkadb.services.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/write")
public class WriteController {

    @Autowired
    WriteService writeService;

    @GetMapping
    void writeFiles(){
        writeService.writeAllFiles();
    }


}
