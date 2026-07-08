import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    private Map<City, List<Theatre>> cityToTheatresMap = new HashMap<>();

    public void addTheatre(Theatre theatre, City city) {
        cityToTheatresMap.computeIfAbsent(city, k -> new ArrayList<>()).add(theatre);
    }

    public List<Theatre> getTheatresByCity(City city) {
        return cityToTheatresMap.getOrDefault(city, new ArrayList<>());
    }
}
