package com.pubSubExample.kafkaProducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    public NewTopic createTopic() {
        return new NewTopic("producer-test-1", 5, (short)1);
                        //manually setting topic-name, partitions, replication-factor
    }

}
