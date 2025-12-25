package com.scaleticket.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingEventConsumer {

    @KafkaListener(topics = "booking-events", groupId = "booking-group")
    public void consume(String message) {
        System.out.println("Processing event: " + message);
    }
}
