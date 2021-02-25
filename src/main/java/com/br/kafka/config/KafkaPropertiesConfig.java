package com.br.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class KafkaPropertiesConfig {

    private static final String LOCAL_SERVER_URL = "127.0.0.1:9092";
    private static final String USER_SERVICE_CONSUMER = "USER_SERVICE_CONSUMER";
    private static final String USER_SERVICE_CONSUMER_GROUP = "USER_SERVICE_CONSUMER_GROUP";

    public  static final String USER_TOPIC_NAME = "KAFKA_USER_TOPIC_EXAMPLE";

    public static Properties getUserProducerProperties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCAL_SERVER_URL);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    public static Properties getUserConsumerProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCAL_SERVER_URL);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, USER_SERVICE_CONSUMER_GROUP);
        //Only use this config if the topic has more than one partition
        //properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, USER_SERVICE_CONSUMER + UUID.randomUUID().toString());
        //Number of messages consumed per time
        //properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }

}
