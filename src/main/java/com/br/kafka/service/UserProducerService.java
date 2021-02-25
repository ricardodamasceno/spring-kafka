package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import com.br.kafka.utils.StringUtils;
import com.br.kafka.vo.UserRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserProducerService {

    @Autowired
    private UserConsumerService userConsumerService;

    public void saveUser(UserRequestVO request) throws ExecutionException, InterruptedException, JsonProcessingException {
        sendMessage(StringUtils.parseObjectToJsonString(request));
        userConsumerService.consumeUserMessages();
    }

    private void sendMessage(String value) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(KafkaPropertiesConfig.getUserProducerProperties());
        var record = new ProducerRecord<String, String>( KafkaPropertiesConfig.USER_TOPIC_NAME, value, value );
        sendEvent(producer, record, value);
    }

    private void sendEvent(KafkaProducer producer, ProducerRecord record, String value) throws ExecutionException, InterruptedException {
        producer.send(record, this::callback).get();
    }

    private void callback(RecordMetadata data, Exception ex){
        if(Objects.nonNull(ex)){
            ex.printStackTrace();
            return;
        }
        log.info("Message was sent to topic: " + data.topic() + " - Partition: " + data.partition() + " - Offset: " + data.offset());
    }

}
