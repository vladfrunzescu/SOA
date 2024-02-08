package com.teach.java_grading.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource() {
        String dbUrl = System.getenv("POSTGRESQL_URI");
        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .username("postgres")
                .password("admin")
                .build();
    }
}
