package com.training.fileskafkadb.controller;

import com.training.fileskafkadb.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class ReadController {

    @Autowired
    ReadService readService;


    @GetMapping
    void readFiles(){
        readService.readAllFiles();
    }
}
