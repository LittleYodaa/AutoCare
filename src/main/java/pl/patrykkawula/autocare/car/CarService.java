package pl.patrykkawula.autocare.car;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.dtos.CarFullDto;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;
import pl.patrykkawula.autocare.car.dtos.CarInfoDto;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;

    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
    }

    public CarSaveDto saveById(CarSaveDto carSaveDto) {
        Car carToSave = carDtoMapper.mapToSave(carSaveDto);
        Car savedCar = carRepository.save(carToSave);
        return carDtoMapper.mapToSave(savedCar);
    }

    public Optional<CarInfoDto> findById(Long id) {
        return carRepository.findById(id)
                .map(carDtoMapper::mapToUpdate);
    }

    public Optional<CarInfoDto> UpdateCar(Long id, CarInfoDto carInfoDto) {
        if (!carRepository.existsById(id)) {
            return Optional.empty();
        }
        carInfoDto.setId(id);
        Car carToUpdate = carDtoMapper.mapToUpdate(carInfoDto);
        Car updatedCar = carRepository.save(carToUpdate);
        return Optional.of(carDtoMapper.mapToUpdate(updatedCar));
    }

    public void updateMileage(CarFullDto carFullDto) {
        Car car = carDtoMapper.map(carFullDto);
        carRepository.save(car);
    }

    public void deleteById(Long id){
        carRepository.deleteById(id);
    }
}
