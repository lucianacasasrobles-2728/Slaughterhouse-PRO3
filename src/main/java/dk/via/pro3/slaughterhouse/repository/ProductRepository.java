package dk.via.pro3.slaughterhouse.repository;

import dk.via.pro3.slaughterhouse.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public ProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveProduct(Product product) {
    String sql = """
        INSERT INTO product
        (product_id, name)
        VALUES (?, ?)
        """;

    jdbcTemplate.update(
        sql,
        product.getProductId(),
        product.getName()
    );
  }

  public void addTrayToProduct(int productId, int trayId) {
    String sql = """
        INSERT INTO product_tray
        (product_id, tray_id)
        VALUES (?, ?)
        """;

    jdbcTemplate.update(sql, productId, trayId);
  }

  public List<Product> findAllProducts() {
    String sql = """
        SELECT product_id, name
        FROM product
        """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new Product(
            rs.getInt("product_id"),
            rs.getString("name")
        )
    );
  }

  public List<Product> findProductsByAnimal(int registrationNumber) {

    String sql = """
        SELECT DISTINCT p.product_id, p.name
        FROM product p
        JOIN product_tray pt
            ON p.product_id = pt.product_id
        JOIN tray_part tp
            ON pt.tray_id = tp.tray_id
        JOIN animal_part ap
            ON tp.part_id = ap.part_id
        WHERE ap.animal_registration_number = ?
        """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new Product(
            rs.getInt("product_id"),
            rs.getString("name")
        ),
        registrationNumber
    );
  }
}