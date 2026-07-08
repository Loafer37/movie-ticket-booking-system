import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieController movieController = new MovieController();
        TheatreController theatreController = new TheatreController();
        BookingController bookingController = new BookingController();

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

        // Step 5: Pick the INOX Barbie show (show ID 1) and book some seats
        Theatre inox = theatreController.getTheatresByCity(City.Bangalore).get(0);
        Show barbieShow = inox.getShows().get(0); // showId = 1

        System.out.println("\n--- Booking seats for " + barbieShow.getMovie().getName()
                + " (Show " + barbieShow.getShowId() + ") ---");

        Booking booking1 = bookingController.bookSeats(barbieShow, Arrays.asList(5, 6, 7), "Sunny");
        System.out.println("Booking created: " + booking1);

        // Step 6: Try booking one of the same seats again -> should fail
        System.out.println("\n--- Trying to double-book seat 6 ---");
        try {
            bookingController.bookSeats(barbieShow, Arrays.asList(6, 8), "Rahul");
        } catch (IllegalStateException e) {
            System.out.println("Booking failed as expected: " + e.getMessage());
        }

        // Step 7: Cancel booking1 and free up its seats
        System.out.println("\n--- Cancelling booking " + booking1.getBookingId() + " ---");
        boolean cancelled = bookingController.cancelBooking(booking1.getBookingId());
        System.out.println("Cancelled successfully: " + cancelled);
        System.out.println("Booking status now: " + bookingController.getBooking(booking1.getBookingId()).getStatus());

        // Step 8: Now that seats are free again, Rahul's booking should succeed
        System.out.println("\n--- Retrying Rahul's booking for seats 6 and 8 ---");
        Booking booking2 = bookingController.bookSeats(barbieShow, Arrays.asList(6, 8), "Rahul");
        System.out.println("Booking created: " + booking2);
    }
}
