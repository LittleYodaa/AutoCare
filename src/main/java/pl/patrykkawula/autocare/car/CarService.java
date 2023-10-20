package pl.patrykkawula.autocare.car;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.dtos.CarFullDto;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;

    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
    }

    public CarSaveDto save(CarSaveDto carSaveDto) {
        Car carToSave = carDtoMapper.map(carSaveDto);
        Car savedCar = carRepository.save(carToSave);
        return carDtoMapper.mapToSave(savedCar);
    }

    public Optional<CarFullDto> find(Long id) {
        return carRepository.findById(id)
                .map(carDtoMapper::map);
    }
}
