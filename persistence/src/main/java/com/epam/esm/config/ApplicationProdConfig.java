package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The type ApplicationProdConfig.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Configuration
@Profile("prod")
public class ApplicationProdConfig {
    private static final String PROPERTIES_FILENAME = "/config/databaseProd.properties";

//    @Bean
//    public HikariDataSource dataSource() {
//        HikariConfig config = new HikariConfig(PROPERTIES_FILENAME);
//        return new HikariDataSource(config);
//    }
}
