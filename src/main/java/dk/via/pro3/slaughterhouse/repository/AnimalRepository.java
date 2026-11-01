
package dk.via.pro3.slaughterhouse.repository;

import dk.via.pro3.slaughterhouse.model.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepository {

  private final JdbcTemplate jdbcTemplate;

  public AnimalRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Part 2 - Keep existing gRPC functionality
  public List<Integer> findAnimalsByProductId(int productId) {
    String sql = """
                SELECT registration_number
                FROM animal_product
                WHERE product_id = ?
                """;

    return jdbcTemplate.queryForList(
        sql, Integer.class, productId
    );
  }

  // Part 3 - Register a new animal
  public void saveAnimal(Animal animal) {
    String sql = """
                INSERT INTO animal
                (registration_number, weight, date, origin)
                VALUES (?, ?, ?, ?)
                """;

    jdbcTemplate.update(
        sql,
        animal.getRegistrationNumber(),
        animal.getWeight(),
        animal.getDate(),
        animal.getOrigin()
    );
  }

  // Convert database rows into Animal objects
  private Animal mapAnimal(java.sql.ResultSet rs, int rowNum)
      throws java.sql.SQLException {

    Date date = rs.getDate("date");

    return new Animal(
        rs.getInt("registration_number"),
        rs.getDouble("weight"),
        date != null ? date.toLocalDate() : null,
        rs.getString("origin")
    );
  }

  // Find an animal by registration number
  public Optional<Animal> findByRegistrationNumber(int number) {
    String sql = """
                SELECT * FROM animal
                WHERE registration_number = ?
                """;

    return jdbcTemplate.query(sql, this::mapAnimal, number)
        .stream()
        .findFirst();
  }

  // Find animals by arrival date
  public List<Animal> findByDate(LocalDate date) {
    String sql = """
                SELECT * FROM animal
                WHERE date = ?
                """;

    return jdbcTemplate.query(sql, this::mapAnimal, date);
  }

  // Find animals by farm
  public List<Animal> findByOrigin(String origin) {
    String sql = """
                SELECT * FROM animal
                WHERE origin = ?
                """;

    return jdbcTemplate.query(sql, this::mapAnimal, origin);
  }
}
