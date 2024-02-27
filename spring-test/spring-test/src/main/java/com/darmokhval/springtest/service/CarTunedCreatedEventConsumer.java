package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarTunedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CarTunedCreatedEventConsumer implements MessageListener<Integer, CarTunedEvent> {

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "order-creation-events-consumer-1")
    @Override
    public void onMessage(ConsumerRecord<Integer, CarTunedEvent> data) {
        log.info("Received message {}", data);
        log.info("Get info about car: producer-{}, id-{}, model-{}, power-{}", data.value().getProducer(), data.value().getId(), data.value().getModel(), data.value().getPower());
    }
}
