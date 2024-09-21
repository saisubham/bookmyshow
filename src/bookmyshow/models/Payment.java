package bookmyshow.models;

import java.util.UUID;

public class Payment {
    private final String id;
    private final String seatId;
    private final String userId;
    private PaymentStatus status;

    public Payment(String seatId, String userId) {
        this.id = UUID.randomUUID().toString();
        this.seatId = seatId;
        this.userId = userId;
        this.status = PaymentStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getUserId() {
        return userId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", seatId='" + seatId + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                '}';
    }
}
