package bookmyshow.models;

public class CinemaHall {
    private String name;
    private String cityName;

    public CinemaHall(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "name='" + name + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
