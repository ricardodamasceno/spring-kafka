package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import com.br.kafka.vo.UserRequestVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserConsumerService {

    @Autowired
    private KafkaService kafkaService;

    public void consumeUserMessages(){

        var records = kafkaService.consumeMessages(KafkaPropertiesConfig.getUserConsumerProperties(), KafkaPropertiesConfig.USER_TOPIC_NAME);

        if(!records.isEmpty()){
            getUserList(records);
            printRecords(records);
        } else {
            log.error("No records found");
        }
    }

    private List<UserRequestVO> getUserList(ConsumerRecords records){
        Gson gson = new GsonBuilder().create();
        List<UserRequestVO> users = new ArrayList<>();

        records.forEach( user -> {
            try{
                String userString = String.valueOf(((ConsumerRecord)user).value());
                users.add(gson.fromJson(userString, UserRequestVO.class));
            } catch (Exception e){
                log.error("Failed to parse string to Json. Exception: " + e.getMessage());
            }
        });

        return users;
    }

    private void printRecords(ConsumerRecords<String, String> records){
        records.forEach(record -> {
            System.out.println("\n----- Message consumed -----");
            System.out.println("Key: " + record.key());
            System.out.println("Value: " + record.value());
        });
    }

}
