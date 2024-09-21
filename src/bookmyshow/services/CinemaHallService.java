package bookmyshow.services;

import java.util.HashMap;
import java.util.Map;

import bookmyshow.models.CinemaHall;

public class CinemaHallService {
    private final Map<String, CinemaHall> cinemaHallMap;

    public CinemaHallService() {
        cinemaHallMap = new HashMap<>();
    }

    public CinemaHall findById(String id) {
        return cinemaHallMap.get(id);
    }

    public CinemaHall addCinemaHall(String name, String cityName) {
        CinemaHall cinemaHall = new CinemaHall(name, cityName);
        cinemaHallMap.put(name, cinemaHall);
        return cinemaHall;
    }

}
