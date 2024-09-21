package bookmyshow.services;

import java.util.HashMap;
import java.util.Map;

import bookmyshow.models.Show;

public class ShowService {
    private final Map<String, Show> showMap;

    public ShowService() {
        showMap = new HashMap<>();
    }

    public Show findById(String id) {
        return showMap.get(id);
    }

    public Show addShow(String name, String cinemaHallId) {
        Show show = new Show(name, cinemaHallId);
        showMap.put(name, show);
        return show;
    }

}
