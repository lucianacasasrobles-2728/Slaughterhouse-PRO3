package dk.via.pro3.slaughterhouse.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalRepository {

  private final JdbcTemplate jdbcTemplate;

  public AnimalRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Integer> findAnimalsByProductId(int productId) {

    String sql = """
                SELECT registration_number
                FROM animal_product
                WHERE product_id = ?
                """;

    return jdbcTemplate.queryForList(
        sql,
        Integer.class,
        productId
    );
  }
}