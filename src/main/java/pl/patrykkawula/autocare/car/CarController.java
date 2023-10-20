package pl.patrykkawula.autocare.car;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.car.dtos.CarFullDto;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;


import java.net.URI;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    ResponseEntity<?> addCar(@RequestBody CarSaveDto carSaveDto) {
        CarSaveDto savedCar = carService.save(carSaveDto);
        URI savedCarUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(savedCarUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CarFullDto> findById (@PathVariable Long id) {
        return carService.find(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

