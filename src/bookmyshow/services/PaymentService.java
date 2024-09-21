package bookmyshow.services;

import java.util.HashMap;
import java.util.Map;

import bookmyshow.models.Payment;
import bookmyshow.models.PaymentStatus;
import bookmyshow.models.Seat;

public class PaymentService {
    private final Map<String, Payment> paymentMap;
    private final SeatService seatService;

    public PaymentService(SeatService seatService) {
        this.seatService = seatService;
        paymentMap = new HashMap<>();
    }

    public Payment findById(String id) {
        return paymentMap.get(id);
    }

    public Payment makePayment(String seatId, String userId) {
        Seat seat = seatService.findById(seatId);
        if (seat.isLocked() || seat.isBooked()) {
            return null;
        }
        Payment payment = new Payment(seatId, userId);
        payment.setStatus(PaymentStatus.DONE);
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

}
