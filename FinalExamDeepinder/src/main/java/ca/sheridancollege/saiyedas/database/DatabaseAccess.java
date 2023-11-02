package ca.sheridancollege.saiyedas.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.saiyedas.beans.Capstone;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<Capstone> findAll() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM capstone";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class));
    }

    public Capstone findById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM capstone WHERE id = :id";
        namedParameters.addValue("id", id);
        List<Capstone> results = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class));
        return results.isEmpty() ? null : results.get(0);
    }

    public Long save(Capstone capstone) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO capstone (name, vote, information) VALUES (:name, :vote, :information)";
        namedParameters.addValue("name", capstone.getName());
        namedParameters.addValue("vote", capstone.getVote());
        namedParameters.addValue("information", capstone.getInformation());
        jdbc.update(query, namedParameters, generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    public void delete(Capstone capstone) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM capstone WHERE id = :id";
        namedParameters.addValue("id", capstone.getId());
        jdbc.update(query, namedParameters);
    }

    public Long count() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT count(*) FROM capstone";
        return jdbc.queryForObject(query, namedParameters, Long.class);
    }

    public void saveAll(List<Capstone> capstoneList) {
        for (Capstone s : capstoneList) {
            save(s);
        }
    }

    public List<Capstone> getCapstoneList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM capstone";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class));
    }
}
