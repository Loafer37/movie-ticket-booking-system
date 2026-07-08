public class MovieFactory {
    public static Movie createMovie(int movieId, String name, int durationInMinutes) {
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setName(name);
        movie.setDurationInMinutes(durationInMinutes);
        return movie;
    }
}
