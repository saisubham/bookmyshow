package bookmyshow.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookmyshow.models.Seat;

public class SeatService {
    private final Map<String, Seat> seatMap;

    public SeatService() {
        this.seatMap = new HashMap<>();
    }

    public Seat findById(String id) {
        return seatMap.get(id);
    }

    public Seat addSeat(String showId, String seatName) {
        Seat seat = new Seat(showId, seatName);
        seatMap.put(seat.getId(), seat);
        return seat;
    }

    public List<Seat> findAllAvailableByShowId(String showId) {
        return seatMap.values().stream()
                .filter(seat -> seat.getShowId().equals(showId)
                        && !seat.isLocked())
                .toList();
    }

    public List<Seat> findAllByShowId(String showId) {
        return seatMap.values().stream()
                .filter(seat -> seat.getShowId().equals(showId))
                .toList();
    }

}
