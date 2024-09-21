package bookmyshow.models;

import java.util.UUID;

public class Booking {
    private final String id;
    private final String userId;
    private final String seatId;

    public Booking(String userId, String seatId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.seatId = seatId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getSeatId() {
        return seatId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", seatId='" + seatId + '\'' +
                '}';
    }
}
