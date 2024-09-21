package bookmyshow.models;

public class Show {
    private final String name;
    private final String cinemaHallId;

    public Show(String name, String cinemaHallId) {
        this.name = name;
        this.cinemaHallId = cinemaHallId;
    }

    public String getName() {
        return name;
    }

    public String getCinemaHallId() {
        return cinemaHallId;
    }

    @Override
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", cinemaHallId='" + cinemaHallId + '\'' +
                '}';
    }
}
