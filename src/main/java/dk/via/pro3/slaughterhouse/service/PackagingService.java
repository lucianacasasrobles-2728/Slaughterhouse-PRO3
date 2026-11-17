package dk.via.pro3.slaughterhouse.service;

import dk.via.pro3.slaughterhouse.model.Product;
import dk.via.pro3.slaughterhouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagingService {

  private final ProductRepository productRepository;

  public PackagingService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void createProduct(Product product) {
    if (product.getProductId() <= 0) {
      throw new IllegalArgumentException("Product id must be positive");
    }

    if (product.getName() == null || product.getName().isBlank()) {
      throw new IllegalArgumentException("Product name is required");
    }

    productRepository.saveProduct(product);
  }

  public void addTrayToProduct(int productId, int trayId) {
    if (productId <= 0) {
      throw new IllegalArgumentException("Product id must be positive");
    }

    if (trayId <= 0) {
      throw new IllegalArgumentException("Tray id must be positive");
    }

    productRepository.addTrayToProduct(productId, trayId);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAllProducts();
  }
}