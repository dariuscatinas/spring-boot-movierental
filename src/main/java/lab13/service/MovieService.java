package lab13.service;

import lab13.domain.Movie;
import lab13.repository.n.plusone.repository.MovieGraphRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import lab13.repository.MovieRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@ComponentScan(basePackages = {"lab13.repository"})
@Service
public class MovieService implements MovieServiceInt {

    private static final Logger log = LoggerFactory.getLogger(
            MovieService.class);


    @Autowired
    private MovieGraphRepository repo;

    /**
     * Adds a {@code Movie} to the corresponding lab13.repository.
     * @param movie
     *              must not be null/ in a sound state
     * @return a boolean, true if successfully added a movie or false otherwise
     */
    public Optional<Movie> addMovie(Movie movie)  {
        log.trace("addMovie movie={}",movie);
        Optional<Movie> addMovie= Optional.of(repo.save(movie));
        log.trace("method finished---");
        return addMovie;
    }

    /**
     * Deletes a {@code Movie} from the corresponding lab13.repository.
     * @param ID
     *          integer, the ID of the movie to delete
     * @return a boolean, true if successfully deleted the movie or false otherwise.
     */
    public Optional<Movie> deleteMovie(long ID) {
        log.trace("deleteMovie id={}",ID);
        Optional<Movie> movie=repo.findById(ID);
        movie.ifPresent(m->repo.deleteById(ID));
        log.trace("method finished----");
        return movie;
    }

    /**
     *
     * @return an {@code Set<Movie>} of the entire collection of movies.
     */
    public Set<Movie> getAllMovies(){
        log.trace("getAllMovies---");
        Set<Movie> movies=StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toSet());
        log.trace("result={}",movies);
        return movies;
    }
    /**
     * filter the movies based on a predice
     * @param predicate a {@code Predicate} predicate which takes in a Movie and returns true if the Client matches the conditions
     * @return A set of movies, which match the predicate
     */
    public Set<Movie> filterCustom(Predicate<? super Movie> predicate){

        return StreamSupport.stream(repo.findAll().spliterator(), false).filter(predicate).collect(Collectors.toSet());
    }

    /**
     * Updates a movie information
     * @param newMovie the movie with the new information
     * @return an {@code Optional} which contains the previous movie information
     */
    @Transactional
    public Optional<Movie> update(long id,Movie newMovie){
        log.trace("updateMovie newMovie={}",newMovie);
        Optional<Movie> dbMovie=repo.findById(id);
        Movie result=dbMovie.orElse(newMovie);
        result.setTitle(newMovie.getTitle());
        result.setGenre(newMovie.getGenre());
        result.setRating(newMovie.getRating());
        log.trace("method finished----");
        return Optional.of(result);
    }

    /**
     * Retrieves a movie by its id
     * @param id, the movie id
     * @return an {@code Optional} containing the movie with the given id or null if it does not exist
     */
    public Optional<Movie> findOne(long id){
        log.trace("findMovie id={}",id);
        Optional<Movie> movie= repo.findById(id);
        log.trace("method finished---");
        return movie;
    }

}
