package com.br.kafka.controller;

import com.br.kafka.service.UserProducerService;
import com.br.kafka.vo.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserProducerService userProducerService;

    @PostMapping
    public void sendMessage(@RequestBody UserRequestVO request) throws ExecutionException, InterruptedException {
        userProducerService.saveUser(request);
    }

    @PostMapping("/insert-data")
    public void insertData() {
        userProducerService.insertData();
    }

}
