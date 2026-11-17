package dk.via.pro3.slaughterhouse.repository;

import dk.via.pro3.slaughterhouse.model.AnimalPart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalPartRepository {

  private final JdbcTemplate jdbcTemplate;

  public AnimalPartRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveAnimalPart(AnimalPart animalPart) {
    String sql = """
        INSERT INTO animal_part
        (type, weight, animal_registration_number)
        VALUES (?, ?, ?)
        """;

    jdbcTemplate.update(
        sql,
        animalPart.getType(),
        animalPart.getWeight(),
        animalPart.getAnimalRegistrationNumber()
    );
  }

  public List<AnimalPart> findPartsByAnimal(int registrationNumber) {
    String sql = """
        SELECT part_id, type, weight, animal_registration_number
        FROM animal_part
        WHERE animal_registration_number = ?
        """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new AnimalPart(
            rs.getInt("part_id"),
            rs.getString("type"),
            rs.getDouble("weight"),
            rs.getInt("animal_registration_number")
        ),
        registrationNumber
    );
  }

  public List<AnimalPart> findAllParts() {
    String sql = """
        SELECT part_id, type, weight, animal_registration_number
        FROM animal_part
        """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new AnimalPart(
            rs.getInt("part_id"),
            rs.getString("type"),
            rs.getDouble("weight"),
            rs.getInt("animal_registration_number")
        )
    );
  }
}