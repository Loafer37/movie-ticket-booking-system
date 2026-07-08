import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingController {
    private Map<Integer, Booking> bookingIdToBookingMap = new HashMap<>();
    private int nextBookingId = 1;

    /**
     * Books the given seat IDs for a show under the given user's name.
     * Throws an exception if any seat is already booked or doesn't exist on that show's screen.
     */
    public Booking bookSeats(Show show, List<Integer> seatIds, String userName) {
        if (show.getScreen() == null) {
            throw new IllegalStateException("This show has no screen/seats configured");
        }

        List<Seat> seatsToBook = new ArrayList<>();

        for (Seat seat : show.getScreen().getSeats()) {
            if (seatIds.contains(seat.getSeatId())) {
                if (seat.isBooked()) {
                    throw new IllegalStateException("Seat " + seat.getSeatId() + " is already booked for this show");
                }
                seatsToBook.add(seat);
            }
        }

        if (seatsToBook.size() != seatIds.size()) {
            throw new IllegalArgumentException("One or more requested seat IDs don't exist on this show's screen");
        }

        // Mark seats as booked
        for (Seat seat : seatsToBook) {
            seat.setBooked(true);
        }

        Booking booking = new Booking();
        booking.setBookingId(nextBookingId++);
        booking.setShow(show);
        booking.setBookedSeats(seatsToBook);
        booking.setUserName(userName);
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingIdToBookingMap.put(booking.getBookingId(), booking);
        return booking;
    }

    /**
     * Cancels an existing booking and frees up its seats.
     * Returns true if cancelled successfully, false if booking doesn't exist or is already cancelled.
     */
    public boolean cancelBooking(int bookingId) {
        Booking booking = bookingIdToBookingMap.get(bookingId);

        if (booking == null || booking.getStatus() == BookingStatus.CANCELLED) {
            return false;
        }

        for (Seat seat : booking.getBookedSeats()) {
            seat.setBooked(false);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return true;
    }

    public Booking getBooking(int bookingId) {
        return bookingIdToBookingMap.get(bookingId);
    }
}
