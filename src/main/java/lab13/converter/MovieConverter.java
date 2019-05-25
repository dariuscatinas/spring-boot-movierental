package lab13.converter;

import lab13.domain.Movie;
import lab13.dto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie= Movie.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .rating(dto.getRating())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto movieDto= MovieDto.builder()
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .rating(movie.getRating())
                .build();
        movieDto.setId(movie.getId());
        return movieDto;
    }
}
