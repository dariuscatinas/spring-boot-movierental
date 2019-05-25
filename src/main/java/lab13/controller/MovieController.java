package lab13.controller;

import lab13.converter.MovieConverter;
import lab13.domain.Movie;
import org.springframework.context.annotation.ComponentScan;
import lab13.service.MovieServiceInt;
import lab13.dto.MovieDto;
import lab13.dto.MoviesDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@ComponentScan(basePackages = {"lab13.service", "lab13.converter"})
@RestController
public class MovieController {

    private static final Logger log= LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieServiceInt movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value="/movies",method = RequestMethod.GET)
    Set<MovieDto> getAllMovies(){
        log.trace("getAllMovies --- method entered");

        Set<Movie> movies=movieService.getAllMovies();
        Set<MovieDto> dtos=movieConverter.convertModelsToDtos(movies);

        log.trace("getAllMovies: resul={}",dtos);

        return dtos;
    }

    @RequestMapping(value="/movies",method = RequestMethod.POST)
    MovieDto saveMovie(@RequestBody MovieDto movieDto){
        log.trace("saveMovie: lab13.dto={}",movieDto);

        Optional<Movie> saved=movieService.addMovie(movieConverter.convertDtoToModel(movieDto));
        MovieDto result=movieConverter.convertModelToDto(saved.get());

        log.trace("saveMovie: result={}",result);

        return result;
    }

    @RequestMapping(value="/movies/{id}",method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto){
        log.trace("updateMovie: id={} lab13.dto={}",id,movieDto);
        Optional<Movie> updated=movieService.update(id,movieConverter.convertDtoToModel(movieDto));
        MovieDto result=movieConverter.convertModelToDto(updated.get());

        log.trace("updateMovie: resul={}",result);

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.trace("delteMovie: id={}", id);
        boolean deleted=false;
        Optional<Movie> movie=movieService.deleteMovie(id);
        if (movie.isPresent())
            deleted=true;
        log.trace("delteMovie --- method finished");
        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/movies/{id}",method = RequestMethod.GET)
    MovieDto findOneMovie(@PathVariable Long id){
        log.trace("findOneMovie: id={}",id);
        boolean found=false;
        MovieDto result=null;
        Optional<Movie> movie=movieService.findOne(id);
        if (movie.isPresent())
            found=true;
        if (found)
            result=movieConverter.convertModelToDto(movie.get());

        log.trace("findOneMovie: lab13.dto={}",result);
        return result;
    }

    @RequestMapping(value="/movies/filterR/{rating}",method = RequestMethod.GET)
    MoviesDtos filterMoviesR(@PathVariable float rating){
        return new MoviesDtos(movieConverter.convertModelsToDtos(
                movieService.getAllMovies().stream().filter(m->m.getRating()>rating).collect(Collectors.toSet())));
    }

    @RequestMapping(value="/movies/filterG/{genre}",method = RequestMethod.GET)
    MoviesDtos filterMoviesR(@PathVariable String genre){
        return new MoviesDtos(movieConverter.convertModelsToDtos(
                movieService.getAllMovies().stream().filter(m->m.getGenre().equals(genre)).collect(Collectors.toSet())));
    }
}
