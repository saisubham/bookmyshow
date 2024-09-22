package bookmyshow.services;

import java.util.HashMap;
import java.util.Map;

import bookmyshow.models.Booking;
import bookmyshow.models.Payment;
import bookmyshow.models.Seat;

public class BookingService {
    private final SeatService seatService;
    private final PaymentService paymentService;
    private final Map<String, Booking> bookingMap;

    public BookingService(SeatService seatService, PaymentService paymentService) {
        this.seatService = seatService;
        this.paymentService = paymentService;
        bookingMap = new HashMap<>();
    }

    public boolean book(String seatId, String userId) {
        Seat seat = seatService.findById(seatId);
        return seat.lock(userId);
    }

    // callback
    public Booking book(String paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment == null) {
            return null;
        }
        String seatId = payment.getSeatId();
        String userId = payment.getUserId();
        Seat seat = seatService.findById(seatId);
        if (!seat.book(userId)) {
            return null;
        }
        Booking booking = new Booking(userId, seatId);
        bookingMap.put(booking.getId(), booking);
        return booking;
    }
}
