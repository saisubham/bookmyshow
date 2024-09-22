package bookmyshow.models;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Seat {
    private final String id;
    private final String name;
    private final String showId;
    private String lockedByUserId;
    private String bookedByUserId;
    private volatile SeatStatus status;
    private Instant lastLockedTime;
    private final int lockDurationSeconds;

    public Seat(String showId, String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.showId = showId;
        status = SeatStatus.FREE;
        lastLockedTime = null;
        lockDurationSeconds = 3;
        lockedByUserId = null;
        bookedByUserId = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShowId() {
        return showId;
    }

    public String getLockedByUserId() {
        return lockedByUserId;
    }

    public synchronized boolean lock(String userId) {
        if (status == SeatStatus.BOOKED || (status == SeatStatus.LOCKED && !userId.equals(lockedByUserId))) {
            return false;
        }
        status = SeatStatus.LOCKED;
        lastLockedTime = Instant.now();
        lockedByUserId = userId;
        return true;
    }

    public synchronized boolean isLocked() {
        if (lastLockedTime != null && ChronoUnit.SECONDS.between(lastLockedTime, Instant.now()) <= lockDurationSeconds) {
            return true;
        }
        status = SeatStatus.FREE;
        lockedByUserId = null;
        return false;
    }

    public synchronized boolean book(String userId) {
        if (status == SeatStatus.BOOKED || (status == SeatStatus.LOCKED && !userId.equals(lockedByUserId))) {
            return false;
        }
        bookedByUserId = userId;
        status = SeatStatus.BOOKED;
        return true;
    }

    public boolean isBooked() {
        return status == SeatStatus.BOOKED;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", showId='" + showId + '\'' +
                ", lockedByUserId='" + lockedByUserId + '\'' +
                ", bookedByUserId='" + bookedByUserId + '\'' +
                ", status=" + status +
                ", lastLockedTime=" + lastLockedTime +
                ", lockDurationSeconds=" + lockDurationSeconds +
                '}';
    }
}
