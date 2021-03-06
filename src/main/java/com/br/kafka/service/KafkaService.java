package com.br.kafka.service;

import com.br.kafka.config.KafkaPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaService<T> {

    public ConsumerRecords<String, T> consumeMessages(Properties consumerProperties, String topicName){
        var consumer = new KafkaConsumer<String, T>(consumerProperties);
        consumer.subscribe(Collections.singletonList(topicName));
        return consumer.poll(Duration.ofMillis(10000));
    }

    public void sendMessage(T object, Properties producerProperties, String topicName) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, T>(producerProperties);
        var record = new ProducerRecord<String, T>(topicName, UUID.randomUUID().toString(), object);
        sendEvent(producer, record);
    }

    private void sendEvent(KafkaProducer producer, ProducerRecord record) throws ExecutionException, InterruptedException {
        producer.send(record, this::callback).get();
    }

    private void callback(RecordMetadata data, Exception ex){
        if(Objects.nonNull(ex)){
            log.error("Failed to send message. Exception: " + ex.getMessage());
            return;
        }
        log.info("Message was sent to topic: " + data.topic() + " - Partition: " + data.partition() + " - Offset: " + data.offset());
    }
}
