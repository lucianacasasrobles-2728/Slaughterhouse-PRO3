package dk.via.pro3.slaughterhouse.controller;

import dk.via.pro3.slaughterhouse.model.AnimalPart;
import dk.via.pro3.slaughterhouse.model.Tray;
import dk.via.pro3.slaughterhouse.service.CuttingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cutting")
public class CuttingController {

  private final CuttingService cuttingService;

  public CuttingController(CuttingService cuttingService) {
    this.cuttingService = cuttingService;
  }

  @PostMapping("/parts")
  public ResponseEntity<?> registerAnimalPart(
      @RequestBody AnimalPart animalPart) {
    try {
      cuttingService.registerAnimalPart(animalPart);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(animalPart);

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());
    }
  }

  @PostMapping("/trays")
  public ResponseEntity<?> createTray(@RequestBody Tray tray) {
    try {
      cuttingService.createTray(tray);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(tray);

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());
    }
  }

  @PostMapping("/trays/{trayId}/parts/{partId}")
  public ResponseEntity<?> addPartToTray(
      @PathVariable int trayId,
      @PathVariable int partId) {
    try {
      cuttingService.addPartToTray(trayId, partId);

      return ResponseEntity
          .ok("Part added to tray");

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());
    }
  }

  @GetMapping("/parts")
  public ResponseEntity<?> getAllParts() {
    return ResponseEntity.ok(cuttingService.getAllParts());
  }

  @GetMapping("/parts/animal/{registrationNumber}")
  public ResponseEntity<?> getPartsByAnimal(
      @PathVariable int registrationNumber) {
    return ResponseEntity.ok(
        cuttingService.getPartsByAnimal(registrationNumber)
    );
  }

  @GetMapping("/trays")
  public ResponseEntity<?> getAllTrays() {
    return ResponseEntity.ok(cuttingService.getAllTrays());
  }
}