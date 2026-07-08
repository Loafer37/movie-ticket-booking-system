import java.util.List;

public class Booking {
    private int bookingId;
    private Show show;
    private List<Seat> bookedSeats;
    private String userName;
    private BookingStatus status;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder seatIds = new StringBuilder();
        for (Seat seat : bookedSeats) {
            seatIds.append(seat.getSeatId()).append(" ");
        }
        return "Booking{" +
                "bookingId=" + bookingId +
                ", movie='" + show.getMovie().getName() + '\'' +
                ", seats=[" + seatIds.toString().trim() + "]" +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                '}';
    }
}
