package com.pubSubExample.kafkaConsumer.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

//    such configs enable to access secured kafka cluster as they are customised & prone to changes
    private Map<String,Object> consumerConfig() {
        Map<String, Object> consumerConfigs = new HashMap<>();
//        zookeeper@2181 && kafka-server@9092
//        NOTE: .IllegalStateException: No group.id found in consumer config, container properties, or @KafkaListener annotation; a group.id is required when group management is used.
//        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group-1");
            //does not work
//        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerConfigs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return consumerConfigs;
    }

    private ConsumerFactory<String,Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>>
            kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }

}
