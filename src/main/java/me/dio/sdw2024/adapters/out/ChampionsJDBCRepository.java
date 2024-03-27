package me.dio.sdw2024.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import me.dio.sdw2024.domain.model.Champions;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

@Repository
public class ChampionsJDBCRepository implements ChampionsRepository {

  // fornece uso ao database
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Champions> rowMapper;

  public ChampionsJDBCRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = (rs, rowNum) -> new Champions(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getString("role"),
        rs.getString("lore"),
        rs.getString("image_url"));
  }

  @SuppressWarnings("null")
  @Override
  public List<Champions> findAll() {
    return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
  }

  @Override
  public Optional<Champions> findById(Long id) {
    String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
    @SuppressWarnings("null")
    Champions champions = jdbcTemplate.queryForObject(sql, rowMapper, id);
    return Optional.ofNullable(champions);
  }
}
