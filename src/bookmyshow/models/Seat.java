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
    private SeatStatus status;
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

    public synchronized void lock(String userId) {
        status = SeatStatus.LOCKED;
        lastLockedTime = Instant.now();
        lockedByUserId = userId;
    }

    public synchronized boolean isLocked() {
        if (lastLockedTime != null && ChronoUnit.SECONDS.between(lastLockedTime, Instant.now()) <= lockDurationSeconds) {
            return true;
        }
        status = SeatStatus.FREE;
        lockedByUserId = null;
        return false;
    }

    public synchronized void book(String userId) {
        bookedByUserId = userId;
        lockedByUserId = null;
        status = SeatStatus.BOOKED;
    }

    public synchronized boolean isBooked() {
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
