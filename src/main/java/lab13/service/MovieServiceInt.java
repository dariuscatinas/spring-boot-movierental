package lab13.service;

import lab13.domain.Movie;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public interface MovieServiceInt extends Service<Long,Movie> {
    
     Optional<Movie> addMovie(Movie movie);
     Optional<Movie> deleteMovie(long ID);
     Set<Movie> getAllMovies();
     Set<Movie> filterCustom(Predicate<? super Movie> predicate);
     Optional<Movie> update(long id,Movie newMovie);
     Optional<Movie> findOne(long id);
}
