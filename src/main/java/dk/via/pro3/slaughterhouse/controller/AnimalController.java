
package dk.via.pro3.slaughterhouse.controller;

import dk.via.pro3.slaughterhouse.model.Animal;
import dk.via.pro3.slaughterhouse.service.AnimalService;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

  private final AnimalService animalService;

  public AnimalController(AnimalService animalService) {
    this.animalService = animalService;
  }

  // Register a new animal
  @PostMapping
  public ResponseEntity<?> registerAnimal(@RequestBody Animal animal) {
    try {
      animalService.registerAnimal(animal);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(animal);

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());

    } catch (DuplicateKeyException e) {
      return ResponseEntity
          .status(HttpStatus.CONFLICT)
          .body("An animal with this registration number already exists");
    }
  }

  // Find one animal
  @GetMapping("/{registrationNumber}")
  public ResponseEntity<Animal> getAnimal(
      @PathVariable int registrationNumber) {

    return animalService.getAnimal(registrationNumber)
        .map(ResponseEntity::ok)
        .orElseGet(() ->
            ResponseEntity.notFound().build());
  }

  // Find animals by arrival date
  @GetMapping("/date/{date}")
  public List<Animal> getAnimalsByDate(
      @PathVariable LocalDate date) {

    return animalService.getAnimalsByDate(date);
  }

  // Find animals by origin
  @GetMapping("/origin/{origin}")
  public List<Animal> getAnimalsByOrigin(
      @PathVariable String origin) {

    return animalService.getAnimalsByOrigin(origin);
  }
}

