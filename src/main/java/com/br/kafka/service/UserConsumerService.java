package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
public class UserConsumerService {

    public void consumeUserMessages(){
        var consumer = new KafkaConsumer<String, String>(KafkaPropertiesConfig.getUserConsumerProperties());
        consumer.subscribe(Collections.singletonList(KafkaPropertiesConfig.USER_TOPIC_NAME));

        var records = consumer.poll(Duration.ofMillis(10000));

        if(!records.isEmpty()){
            records.forEach(record -> {
                System.out.println("\n----- Message consumed -----");
                System.out.println(record.key());
                System.out.println(record.value());
            });

        }


    }

}
