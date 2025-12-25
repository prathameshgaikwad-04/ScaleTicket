package com.scaleticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scaleticket.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {

    @Modifying
    @Query("""
        UPDATE Seat s
        SET s.status = 'RESERVED', s.reservedBy = :userId
        WHERE s.seatId = :seatId AND s.status = 'AVAILABLE'
    """)
    int reserveSeat(String seatId, Long userId);
}
