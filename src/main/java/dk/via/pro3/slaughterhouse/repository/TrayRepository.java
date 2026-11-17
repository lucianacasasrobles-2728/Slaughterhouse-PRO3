
package dk.via.pro3.slaughterhouse.repository;

import dk.via.pro3.slaughterhouse.model.Tray;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TrayRepository {

  private final JdbcTemplate jdbcTemplate;

  public TrayRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveTray(Tray tray) {
    String sql = """
            INSERT INTO tray (part_type, max_weight)
            VALUES (?, ?)
            """;

    jdbcTemplate.update(
        sql,
        tray.getPartType(),
        tray.getMaxWeight()
    );
  }

  @Transactional
  public void addPartToTray(int trayId, int partId) {

    // Lock the tray while checking its capacity.
    List<Tray> trays = jdbcTemplate.query("""
                SELECT tray_id, part_type, max_weight
                FROM tray
                WHERE tray_id = ?
                FOR UPDATE
                """,
        (rs, rowNum) -> new Tray(
            rs.getInt("tray_id"),
            rs.getString("part_type"),
            rs.getDouble("max_weight")
        ),
        trayId
    );

    if (trays.isEmpty()) {
      throw new IllegalArgumentException("Tray not found");
    }

    Tray tray = trays.get(0);

    // Find the animal part.
    List<PartInfo> parts = jdbcTemplate.query("""
                SELECT type, weight
                FROM animal_part
                WHERE part_id = ?
                """,
        (rs, rowNum) -> new PartInfo(
            rs.getString("type"),
            rs.getDouble("weight")
        ),
        partId
    );

    if (parts.isEmpty()) {
      throw new IllegalArgumentException("Animal part not found");
    }

    PartInfo part = parts.get(0);

    // Check if the part is already assigned.
    Integer assignedCount = jdbcTemplate.queryForObject("""
                SELECT COUNT(*)
                FROM tray_part
                WHERE part_id = ?
                """,
        Integer.class,
        partId
    );

    if (assignedCount != null && assignedCount > 0) {
      throw new IllegalArgumentException(
          "Animal part is already assigned to a tray"
      );
    }

    // Check that the types match.
    if (!tray.getPartType().equalsIgnoreCase(part.type())) {
      throw new IllegalArgumentException(
          "Part type does not match tray type"
      );
    }

    // Calculate the current weight of the tray.
    Double currentWeight = jdbcTemplate.queryForObject("""
                SELECT COALESCE(SUM(ap.weight), 0)
                FROM tray_part tp
                JOIN animal_part ap
                    ON tp.part_id = ap.part_id
                WHERE tp.tray_id = ?
                """,
        Double.class,
        trayId
    );

    double totalWeight =
        (currentWeight == null ? 0 : currentWeight)
            + part.weight();

    // Check the maximum allowed weight.
    if (totalWeight > tray.getMaxWeight() + 0.000001) {
      throw new IllegalArgumentException(
          "Tray maximum weight exceeded"
      );
    }

    // Save the relationship.
    try {
      jdbcTemplate.update("""
                    INSERT INTO tray_part (tray_id, part_id)
                    VALUES (?, ?)
                    """,
          trayId,
          partId
      );
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException(
          "Cannot assign this part to the tray", e
      );
    }
  }

  public List<Tray> findAllTrays() {
    String sql = """
            SELECT tray_id, part_type, max_weight
            FROM tray
            """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new Tray(
            rs.getInt("tray_id"),
            rs.getString("part_type"),
            rs.getDouble("max_weight")
        )
    );
  }

  private record PartInfo(String type, double weight) {
  }
}
