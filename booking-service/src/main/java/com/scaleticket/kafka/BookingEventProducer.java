package com.scaleticket.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BookingEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSeatReserved(Long eventId, String seatId, Long userId) {
        kafkaTemplate.send(
            "booking-events",
            "SEAT_RESERVED | " + eventId + " | " + seatId + " | " + userId
        );
    }
}
