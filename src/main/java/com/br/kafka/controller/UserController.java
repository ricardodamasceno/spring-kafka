package com.br.kafka.controller;

import com.br.kafka.service.UserProducerService;
import com.br.kafka.vo.UserRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    private UserProducerService userProducerService;

    @PostMapping
    public void sendMessage(@RequestBody UserRequestVO request) throws ExecutionException, InterruptedException, JsonProcessingException {
        userProducerService.saveUser(request);
    }

}
