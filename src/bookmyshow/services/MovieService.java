package bookmyshow.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookmyshow.models.Movie;

public class MovieService {
    private final Map<String, Movie> movieMap;

    public MovieService() {
        this.movieMap = new HashMap<>();
    }

    public Movie findById(String id) {
        return movieMap.get(id);
    }

    public Movie addMovie(String name) {
        Movie movie = new Movie(name);
        movieMap.put(name, movie);
        return movie;
    }

    public List<Movie> findAll() {
        return movieMap.values().stream().toList();
    }

}
