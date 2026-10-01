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

  public List<Product> findProductsByAnimal(int registrationNumber) {

    String sql = """
                SELECT p.product_id, p.name
                FROM product p
                JOIN animal_product ap
                    ON p.product_id = ap.product_id
                WHERE ap.registration_number = ?
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