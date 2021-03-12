package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import com.br.kafka.utils.FileUtils;
import com.br.kafka.vo.UserRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    }

    public void insertData() {
        List<UserRequestVO> users = FileUtils.loadUsersFromFile("src/main/resources/data/insert-users.json");

        users.forEach( user -> {
            try {
                kafkaService.sendMessage(user, KafkaPropertiesConfig.getUserProducerProperties(), KafkaPropertiesConfig.USER_TOPIC_NAME);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
