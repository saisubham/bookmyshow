package bookmyshow.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookmyshow.models.City;

public class CityService {
    private final Map<String, City> cityMap;

    public CityService() {
        cityMap = new HashMap<>();
    }

    public City findById(String id) {
        return cityMap.get(id);
    }

    public City addCity(String name) {
        City city = new City(name);
        cityMap.put(name, city);
        return city;
    }

    public List<City> findAll() {
        return cityMap.values().stream().toList();
    }

}
