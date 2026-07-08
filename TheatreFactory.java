import java.util.List;

public class TheatreFactory {
    public static Theatre createTheatre(int theatreId, String name, City city, List<Show> shows) {
        Theatre theatre = new Theatre();
        theatre.setTheatreId(theatreId);
        theatre.setName(name);
        theatre.setCity(city);
        theatre.setShows(shows);
        return theatre;
    }
}
