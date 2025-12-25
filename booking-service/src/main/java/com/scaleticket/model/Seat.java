package com.scaleticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    private String seatId;

    private Long eventId;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long reservedBy;

    @Version
    private Integer version;

    // ---------- Getters & Setters ----------

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Long getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Long reservedBy) {
        this.reservedBy = reservedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}



