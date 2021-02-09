package info.freeit.photostorage.repository;

import info.freeit.photostorage.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class  PictureRepositoryCustomImpl implements PictureRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String findAllByName_SQL = "select * from Picture where name=:targetName";
    private final MapSqlParameterSource parameters = new MapSqlParameterSource();

    @Override
    public List<Picture> findAllByName(String name) {
        parameters.addValue("targetName", name);
        return jdbcTemplate.query(findAllByName_SQL, parameters, new BeanPropertyRowMapper<>(Picture.class));
    }
}
