import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieController movieController = new MovieController();
        TheatreController theatreController = new TheatreController();

        // Step 1: Create movies for each city
        List<Movie> movies = DataInitializer.createMovies(movieController);
        System.out.println("Movies created: " + movies);

        // Step 2: Create theatres with shows for each city
        DataInitializer.createTheatres(movieController, theatreController);

        // Step 3: Print movies available in Delhi
        System.out.println("\nMovies available in Delhi:");
        for (Movie movie : movieController.getMoviesByCity(City.Delhi)) {
            System.out.println(" - " + movie.getName());
        }

        // Step 4: Print theatres available in Bangalore
        System.out.println("\nTheatres available in Bangalore:");
        for (Theatre theatre : theatreController.getTheatresByCity(City.Bangalore)) {
            System.out.println(" - " + theatre.getName() + " (Theatre ID: " + theatre.getTheatreId() + ")");
            for (Show show : theatre.getShows()) {
                System.out.println("    Show " + show.getShowId() + ": " + show.getMovie().getName()
                        + " at " + show.getShowStartTime() + ":00");
            }
        }
    }
}
