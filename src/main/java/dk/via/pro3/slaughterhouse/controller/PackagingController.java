package dk.via.pro3.slaughterhouse.controller;

import dk.via.pro3.slaughterhouse.model.Product;
import dk.via.pro3.slaughterhouse.service.PackagingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packaging")
public class PackagingController {

  private final PackagingService packagingService;

  public PackagingController(PackagingService packagingService) {
    this.packagingService = packagingService;
  }

  @PostMapping("/products")
  public ResponseEntity<?> createProduct(@RequestBody Product product) {
    try {
      packagingService.createProduct(product);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(product);

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());
    }
  }

  @PostMapping("/products/{productId}/trays/{trayId}")
  public ResponseEntity<?> addTrayToProduct(
      @PathVariable int productId,
      @PathVariable int trayId) {
    try {
      packagingService.addTrayToProduct(productId, trayId);

      return ResponseEntity.ok("Tray added to product");

    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .body(e.getMessage());
    }
  }

  @GetMapping("/products")
  public ResponseEntity<?> getAllProducts() {
    return ResponseEntity.ok(packagingService.getAllProducts());
  }
}