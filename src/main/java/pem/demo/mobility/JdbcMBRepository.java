package pem.demo.mobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcMBRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMBRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


}
