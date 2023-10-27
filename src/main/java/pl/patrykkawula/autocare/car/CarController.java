package pl.patrykkawula.autocare.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.car.dtos.CarDto;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final ObjectMapper objectMapper;

    public CarController(CarService carService, ObjectMapper objectMapper) {
        this.carService = carService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<?> addCar(@RequestBody CarDto carDto) {
        CarDto savedCar = carService.saveCar(carDto);
        URI savedCarUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.id())
                .toUri();
        return ResponseEntity.created(savedCarUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<CarDto> updateCarById(@PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.UpdateCar(id, carDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateCarFields(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            CarDto carSaveDto = carService.findById(id).orElseThrow();
            CarDto carPatched = applyPatch(carSaveDto, patch);
            carService.updateCarFields(carPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CarDto applyPatch(CarDto carDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonCarNode = objectMapper.valueToTree(carDto);
        JsonNode carSaveDtoPatchedNode = patch.apply(jsonCarNode);
        return objectMapper.treeToValue(carSaveDtoPatchedNode, CarDto.class);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

