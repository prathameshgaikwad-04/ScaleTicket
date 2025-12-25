package com.scaleticket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scaleticket.kafka.BookingEventProducer;
import com.scaleticket.redis.RedisLockService;
import com.scaleticket.repository.SeatRepository;

@Service
@Transactional
public class BookingService {

    private final SeatRepository seatRepository;
    private final RedisLockService lockService;
    private final BookingEventProducer producer;

    // ✅ Explicit constructor (Spring will inject dependencies)
    public BookingService(
            SeatRepository seatRepository,
            RedisLockService lockService,
            BookingEventProducer producer) {

        this.seatRepository = seatRepository;
        this.lockService = lockService;
        this.producer = producer;
    }

    public String bookSeat(Long eventId, String seatId, Long userId) {

        String lockKey = "seat:" + eventId + ":" + seatId;

        if (!lockService.lockSeat(lockKey, userId.toString())) {
            return "Seat temporarily locked";
        }

        try {
            int updated = seatRepository.reserveSeat(seatId, userId);
            if (updated == 0) {
                return "Seat already booked";
            }

            producer.publishSeatReserved(eventId, seatId, userId);
            return "Seat reserved successfully";

        } finally {
            lockService.releaseLock(lockKey, userId.toString());
        }
    }
}
