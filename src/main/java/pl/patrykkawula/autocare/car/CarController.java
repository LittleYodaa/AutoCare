package pl.patrykkawula.autocare.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.car.dtos.CarFullDto;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;
import pl.patrykkawula.autocare.car.dtos.CarInfoDto;


import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final ObjectMapper objectMapper;

    public CarController(CarService carService, ObjectMapper objectMapper) {
        this.carService = carService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<?> addCar(@RequestBody CarSaveDto carSaveDto) {
        CarSaveDto savedCar = carService.saveById(carSaveDto);
        URI savedCarUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(savedCarUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CarInfoDto> getCarById(@PathVariable Long id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<CarInfoDto> updateCarById(@PathVariable Long id, @RequestBody CarInfoDto carInfoDto) {
        return carService.UpdateCar(id, carInfoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateCarMileage(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            CarInfoDto carInfoDto = carService.findById(id).orElseThrow();
            CarFullDto carPatched = applyPatch(carInfoDto, patch);
            carService.updateMileage(carPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CarFullDto applyPatch(CarInfoDto carInfoDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonCarNode = objectMapper.valueToTree(carInfoDto);
        JsonNode carFullDtoPatchedNode = patch.apply(jsonCarNode);
        return objectMapper.treeToValue(carFullDtoPatchedNode, CarFullDto.class);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

