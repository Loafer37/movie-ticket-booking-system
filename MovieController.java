import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    private Map<City, List<Movie>> cityToMoviesMap = new HashMap<>();
    private Map<String, Movie> nameToMovieMap = new HashMap<>();

    public void addMovie(Movie movie, City city) {
        cityToMoviesMap.computeIfAbsent(city, k -> new ArrayList<>()).add(movie);
        nameToMovieMap.put(movie.getName(), movie);
    }

    public Movie getMovieByName(String name) {
        return nameToMovieMap.get(name);
    }

    public List<Movie> getMoviesByCity(City city) {
        return cityToMoviesMap.getOrDefault(city, new ArrayList<>());
    }
}
