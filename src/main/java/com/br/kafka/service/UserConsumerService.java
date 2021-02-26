package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumerService {

    @Autowired
    private KafkaService kafkaService;

    public void consumeUserMessages(){

        var records = kafkaService.consumeMessages(KafkaPropertiesConfig.getUserConsumerProperties(), KafkaPropertiesConfig.USER_TOPIC_NAME);

        if(!records.isEmpty()){
            printRecords(records);
        } else {
            log.error("No records found");
        }
    }

    private void printRecords(ConsumerRecords<String, String> records){
        records.forEach(record -> {
            System.out.println("\n----- Message consumed -----");
            System.out.println("Key: " + record.key());
            System.out.println("Value: " + record.value());
        });
    }

}
