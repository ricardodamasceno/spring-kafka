package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import com.br.kafka.vo.UserRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserProducerService {

    @Autowired
    private UserConsumerService userConsumerService;

    @Autowired
    private KafkaService kafkaService;

    public void saveUser(UserRequestVO request) throws ExecutionException, InterruptedException {
        kafkaService.sendMessage(request, KafkaPropertiesConfig.getUserProducerProperties(), KafkaPropertiesConfig.USER_TOPIC_NAME);
        userConsumerService.consumeUserMessages();
    }





}
