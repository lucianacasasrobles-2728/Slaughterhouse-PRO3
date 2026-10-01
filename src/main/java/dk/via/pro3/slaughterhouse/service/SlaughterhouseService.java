package dk.via.pro3.slaughterhouse.service;

import dk.via.pro3.slaughterhouse.model.Product;
import dk.via.pro3.slaughterhouse.repository.AnimalRepository;
import dk.via.pro3.slaughterhouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlaughterhouseService {

  private final AnimalRepository animalRepository;
  private final ProductRepository productRepository;

  public SlaughterhouseService(
      AnimalRepository animalRepository,
      ProductRepository productRepository) {

    this.animalRepository = animalRepository;
    this.productRepository = productRepository;
  }

  public List<Integer> getAnimalsByProduct(int productId) {
    return animalRepository.findAnimalsByProductId(productId);
  }

  public List<Product> getProductsByAnimal(int registrationNumber) {
    return productRepository.findProductsByAnimal(registrationNumber);
  }
}