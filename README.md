# 🎬 Movie Ticket Booking System

A Java-based movie ticket booking system built using core Object-Oriented Design principles. It supports multi-city theatre and show management, along with seat booking and cancellation — similar in spirit to platforms like BookMyShow.

## Features

- **Multi-city support** — movies and theatres are organized city-wise (e.g. Bangalore, Delhi)
- **Movie & Theatre management** — add movies to cities, and theatres with multiple shows
- **Seat booking** — book one or more seats for a specific show
- **Duplicate booking prevention** — the system rejects attempts to book a seat that's already taken
- **Booking cancellation** — cancel a booking and automatically free up its seats for others to book
- **Factory pattern** — used to cleanly construct `Movie` and `Theatre` objects

## Project Structure

```
MovieBookingProject/
├── City.java                 # Enum for supported cities
├── Movie.java                # Movie model
├── MovieFactory.java         # Factory to create Movie objects
├── MovieController.java      # Manages movies per city
├── Screen.java                # Represents a theatre screen and its seats
├── Seat.java                  # Seat model with booked/available status
├── Show.java                  # A movie show at a specific time, tied to a Screen
├── Theatre.java                # Theatre model with a list of shows
├── TheatreFactory.java         # Factory to create Theatre objects
├── TheatreController.java      # Manages theatres per city
├── Booking.java                 # Represents a confirmed/cancelled booking
├── BookingStatus.java            # Enum: CONFIRMED / CANCELLED
├── BookingController.java         # Core booking + cancellation logic
├── DataInitializer.java            # Seeds sample data (movies, theatres, shows, seats)
└── Main.java                        # Entry point demonstrating the full flow
```

## Design Overview

- **Controllers** (`MovieController`, `TheatreController`, `BookingController`) hold the business logic and in-memory data (using Maps), keeping models as plain data holders.
- **Factories** (`MovieFactory`, `TheatreFactory`) centralize object creation so the rest of the code doesn't need to know construction details.
- Each `Show` is linked to its own `Screen`, which has 100 seats — so seat availability is tracked per show, not shared across shows.

## How to Run

**Prerequisites:** Java JDK 17 or higher installed ([Adoptium Temurin](https://adoptium.net/) recommended).

1. Clone or download this repository
2. Open a terminal in the project folder
3. Compile all files:
   ```bash
   javac *.java
   ```
4. Run the program:
   ```bash
   java Main
   ```

## Sample Output

```
Movies created: [Movie{movieId=1, name='BARBIE', durationInMinutes=128}, ...]

Movies available in Delhi:
 - BARBIE
 - OPPENHEIMER

Theatres available in Bangalore:
 - INOX (Theatre ID: 1)
    Show 1: BARBIE at 10:00
    Show 2: OPPENHEIMER at 18:00

--- Booking seats for BARBIE (Show 1) ---
Booking created: Booking{bookingId=1, movie='BARBIE', seats=[5 6 7], userName='Sunny', status=CONFIRMED}

--- Trying to double-book seat 6 ---
Booking failed as expected: Seat 6 is already booked for this show

--- Cancelling booking 1 ---
Cancelled successfully: true
Booking status now: CANCELLED

--- Retrying Rahul's booking for seats 6 and 8 ---
Booking created: Booking{bookingId=2, movie='BARBIE', seats=[6 8], userName='Rahul', status=CONFIRMED}
```

## Possible Future Enhancements

- Show available (unbooked) seats before booking
- Ticket pricing based on seat category (Silver/Gold/Platinum)
- Persist bookings to a database instead of in-memory storage
- REST API layer using Spring Boot

## Author

**Sunny Pradhan**
[GitHub: @Loafer37](https://github.com/Loafer37)
