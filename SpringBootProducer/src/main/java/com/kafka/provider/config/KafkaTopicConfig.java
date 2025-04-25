package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configurations = new HashMap<>();

        //CLEANUP_POLICY_DELETE: despues de cierto tiempo si ya no se necesita, el mensaje se borra
        //CLEANUP_POLICY_COMPACT: conserva el mensaje mas actual y borra los repetidos
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_COMPACT);
        //RETENTION_MS_CONFIG: tiempo retencion mensajes en milisegundos (default:-1 = nunca)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        //SEGMENT_BYTES_CONFIG: Define el maximo del segmento en bytes
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        //MAX_MESSAGE_BYTES_CONFIG: Peso o tamanio maximo de cada mensaje en bytes (1Mb por defecto)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1024");

        return TopicBuilder.name("fdx-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();

    }
}
