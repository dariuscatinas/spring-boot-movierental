package lab13.controller;

import lab13.converter.RentalConverter;
import lab13.domain.NewRental;
import org.springframework.context.annotation.ComponentScan;
import lab13.service.RentalService;
import lab13.dto.RentalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@ComponentScan(basePackages = {"lab13.service", "lab13.converter"})
@RestController
public class RentalController {
    private static final Logger log= LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalConverter rentalConverter;

    @RequestMapping(value="/rentals",method = RequestMethod.GET)
    Set<RentalDto> getAll(){
        Set<NewRental> rentals = rentalService.getAll();
        Set<RentalDto> rentalDtos = rentalConverter.convertModelsToDtos(rentals);
        return rentalDtos;
    }
    @RequestMapping(value="/rentals/add", method = RequestMethod.POST)
    RentalDto save(@RequestBody RentalDto rentalDto){
        NewRental rental = rentalConverter.convertDtoToModel(rentalDto);
        Optional<NewRental> saved = rentalService.add(rental);
        return rentalConverter.convertModelToDto(saved.get());
    }

    @RequestMapping(value="/rentals/{id}", method = RequestMethod.GET)
    @Transactional
    RentalDto getOne(@PathVariable Long id){
        Optional<NewRental> rentalO = rentalService.getOne(id);
        if (rentalO.isPresent()){
            return rentalConverter.convertModelToDto(rentalO.get());
        }
        return null;
    }

    @RequestMapping(value = "/rentals/put/{id}", method = RequestMethod.PUT)
    Boolean update(@PathVariable long id, @RequestBody RentalDto newRental){
        log.debug("--- update --- id: " + id + " newRental " + newRental);
        NewRental rental = rentalConverter.convertDtoToModel(newRental);
        log.debug(rental.toString());
        return rentalService.update(id, rental).isPresent();

    }

    @RequestMapping(value = "/rentals/delete/{id}", method = RequestMethod.DELETE)
    Boolean delete(@PathVariable long id){
        return !rentalService.delete(id).isPresent();
    }


}
