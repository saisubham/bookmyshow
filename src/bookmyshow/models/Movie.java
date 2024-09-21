package bookmyshow.models;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    private String name;
    private Set<String> cityNames;
    private Set<String> shows;

    public Movie(String name) {
        this.name = name;
        cityNames = new HashSet<>();
        shows = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getShows() {
        return shows;
    }

    public Set<String> getCityNames() {
        return cityNames;
    }

    public void addCityAndShow(String cityName, String showName) {
        shows.add(showName);
        cityNames.add(cityName);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", cityNames=" + cityNames +
                ", shows=" + shows +
                '}';
    }
}
