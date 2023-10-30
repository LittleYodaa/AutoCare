package pl.patrykkawula.autocare.car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.exception.CarNotFoundException;
import pl.patrykkawula.autocare.user.UserService;

import java.util.List;

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
        Car carToSave = carDtoMapper.map(carDto);
        Car savedCar = carRepository.save(carToSave);
        userService.countUsersCar(savedCar.getUser().getId());
        return carDtoMapper.mapToCarDto(savedCar);
    }

    public CarDto findById(Long id) {
        return carRepository.findById(id)
                .map(carDtoMapper::mapToCarDto).orElseThrow(() -> new CarNotFoundException(id));
    }

    public CarDto updateCar(Long id, CarDto carDto) {
        Car carToUpdate = carDtoMapper.map(carDto);
        carToUpdate.setId(id);
        Car updatedCar = carRepository.save(carToUpdate);
        return carDtoMapper.mapToCarDto(updatedCar);
    }

    public void updateCarFields(CarDto carDto) {
        Car car = carDtoMapper.map(carDto);
        car.setId(carDto.id());
        carRepository.save(car);
    }

    public void deleteById(Long id){
        Car carToDelete = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(carToDelete);
        userService.countUsersCar(id);
    }
}
