package pl.patrykkawula.autocare.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;

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
        CarSaveDto savedCar = carService.saveCar(carSaveDto);
        URI savedCarUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(savedCarUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CarSaveDto> getCarById(@PathVariable Long id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<CarSaveDto> updateCarById(@PathVariable Long id, @RequestBody CarSaveDto carSaveDto) {
        return carService.UpdateCar(id, carSaveDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateCarFields(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            CarSaveDto carSaveDto = carService.findById(id).orElseThrow();
            CarSaveDto carPatched = applyPatch(carSaveDto, patch);
            carService.updateCarFields(carPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CarSaveDto applyPatch(CarSaveDto carSaveDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonCarNode = objectMapper.valueToTree(carSaveDto);
        JsonNode carFullDtoPatchedNode = patch.apply(jsonCarNode);
        return objectMapper.treeToValue(carFullDtoPatchedNode, CarSaveDto.class);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

