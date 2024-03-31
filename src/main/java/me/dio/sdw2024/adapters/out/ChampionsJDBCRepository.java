package me.dio.sdw2024.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import me.dio.sdw2024.domain.model.Champion;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

@Repository
public class ChampionsJDBCRepository implements ChampionsRepository {

  // fornece uso ao database
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Champion> rowMapper;

  public ChampionsJDBCRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = (rs, rowNum) -> new Champion(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getString("role"),
        rs.getString("lore"),
        rs.getString("image_url"));
  }

  @SuppressWarnings("null")
  @Override
  public List<Champion> findAll() {
    return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
  }

  @Override
  public Optional<Champion> findById(Long id) {
    String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
    @SuppressWarnings("null")
    List<Champion> champions = jdbcTemplate.query(sql, rowMapper, id);
    return champions.stream().findFirst();
  }
}
