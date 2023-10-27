package pl.patrykkawula.autocare.car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.user.UserService;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;
    private final UserService userService;

    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper, UserService userService) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
        this.userService = userService;
    }

    @Transactional
    public CarDto saveCar(CarDto carDto) {
        Car carToSave = carDtoMapper.mapToSave(carDto);
        Car savedCar = carRepository.save(carToSave);
        userService.countUsersCar(savedCar.getUser().getId());
        return carDtoMapper.mapToSave(savedCar);
    }

    public Optional<CarDto> findById(Long id) {
        return carRepository.findById(id)
                .map(carDtoMapper::mapToSave);
    }

    public Optional<CarDto> UpdateCar(Long id, CarDto carDto) {
        if (!carRepository.existsById(id)) {
            return Optional.empty();
        }
        Car carToUpdate = carDtoMapper.mapToSave(carDto);
        carToUpdate.setId(id);
        Car updatedCar = carRepository.save(carToUpdate);
        return Optional.of(carDtoMapper.mapToSave(updatedCar));
    }

    public void updateCarFields(CarDto carDto) {
        Car car = carDtoMapper.mapToSave(carDto);
        car.setId(carDto.id());
        carRepository.save(car);
    }

    public void deleteById(Long id){
        carRepository.deleteById(id);
        userService.countUsersCar(id);
    }
}
