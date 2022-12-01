package lesson3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("lesson3.SpringConfig")
public class SpringConfig {
    @Autowired
    JdbcTemplate jdbcTemplate;
    List<String> list = new ArrayList<String>();
    void run() {
        String url = jdbcTemplate.execute((ConnectionCallback<String>) con -> con.getMetaData().getURL());
        System.out.println(url);
        jdbcTemplate.execute("CREATE TABLE Product(name VARCHAR(100), price DOUBLE)");
        jdbcTemplate.update("INSERT INTO Product VALUES('Milk', 3.5)");
        jdbcTemplate.update("INSERT INTO Product VALUES('Smetana', 36)");
        list = jdbcTemplate.queryForList("SELECT name FROM Product", String.class);
        System.out.println(list);
    }
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
