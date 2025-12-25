package com.scaleticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaleticket.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    // ✅ Explicit constructor (Spring injects this)
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveSeat(
            @RequestParam Long eventId,
            @RequestParam String seatId,
            @RequestParam Long userId) {

        return ResponseEntity.ok(
            bookingService.bookSeat(eventId, seatId, userId)
        );
    }
}

