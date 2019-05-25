package lab13.converter;

import lab13.domain.Client;
import lab13.domain.Movie;
import lab13.domain.NewRental;
import lab13.dto.RentalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalConverter  extends BaseConverter<NewRental, RentalDto> {
    @Autowired
    ClientConverter clientConverter;

    @Autowired
    MovieConverter movieConverter;

    @Override
    public NewRental convertDtoToModel(RentalDto dto) {
        LocalDate start = LocalDate.of(2019, dto.getStartMonth(), dto.getStartDate());
        LocalDate end = LocalDate.of(2019, dto.getEndMonth(), dto.getEndDate());
        Client client = clientConverter.convertDtoToModel(dto.getClient());
        Movie movie = movieConverter.convertDtoToModel(dto.getMovie());
        NewRental rental = NewRental.builder()
                .client(client)
                .movie(movie)
                .startDate(start)
                .endDate(end)
                .build();
        rental.setId(dto.getId());
        return rental;
    }

    @Override
    public RentalDto convertModelToDto(NewRental newRental) {
        Client client = newRental.getClient();
        Movie movie = newRental.getMovie();
        LocalDate start = newRental.getStartDate();
        LocalDate end = newRental.getEndDate();

        RentalDto rentalDto = RentalDto.builder()
                .client(clientConverter.convertModelToDto(client))
                .movie(movieConverter.convertModelToDto(movie))
                .startDate(start.getDayOfMonth())
                .startMonth(start.getMonthValue())
                .endDate(end.getDayOfMonth())
                .endMonth(end.getMonthValue())
                .build();
        rentalDto.setId(newRental.getId());
        return rentalDto;

    }
}
