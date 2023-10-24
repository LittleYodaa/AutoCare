package pl.patrykkawula.autocare.car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;
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
    public CarSaveDto saveCar(CarSaveDto carSaveDto) {
        Car carToSave = carDtoMapper.mapToSave(carSaveDto);
        Car savedCar = carRepository.save(carToSave);
        userService.countUsersCar(savedCar.getUser().getId());
        return carDtoMapper.mapToSave(savedCar);
    }

    public Optional<CarSaveDto> findById(Long id) {
        return carRepository.findById(id)
                .map(carDtoMapper::mapToSave);
    }

    public Optional<CarSaveDto> UpdateCar(Long id, CarSaveDto carSaveDto) {
        if (!carRepository.existsById(id)) {
            return Optional.empty();
        }
        Car carToUpdate = carDtoMapper.mapToSave(carSaveDto);
        carToUpdate.setId(id);
        Car updatedCar = carRepository.save(carToUpdate);
        return Optional.of(carDtoMapper.mapToSave(updatedCar));
    }

    public void updateCarFields(CarSaveDto carSaveDto) {
        Car car = carDtoMapper.mapToSave(carSaveDto);
        car.setId(carSaveDto.getId());
        carRepository.save(car);
    }

    public void deleteById(Long id){
        carRepository.deleteById(id);
        userService.countUsersCar(id);
    }
}
