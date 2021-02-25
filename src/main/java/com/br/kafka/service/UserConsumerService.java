package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
@Slf4j
public class UserConsumerService {

    public void consumeUserMessages(){
        var consumer = new KafkaConsumer<String, String>(KafkaPropertiesConfig.getUserConsumerProperties());
        consumer.subscribe(Collections.singletonList(KafkaPropertiesConfig.USER_TOPIC_NAME));

        var records = consumer.poll(Duration.ofMillis(10000));
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
