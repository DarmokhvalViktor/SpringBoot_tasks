package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarTunedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CarTunedEventProducer {
    @Value("${spring.kafka.producer.topic}")
    private String carTunedEventsTopic;

    private final KafkaTemplate<Integer, CarTunedEvent> kafkaTemplate;

    public void carTunedCreatedEvent(CarTunedEvent event) {
        log.info("Sending {} ", event);
        kafkaTemplate.send(carTunedEventsTopic, event);
    }
}
