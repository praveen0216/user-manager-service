package com.management.user.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
@Import({LiquibaseAutoConfiguration.class})
@ConditionalOnBean(PostgreSQLContainer.class)
public class TestDatabaseConfig {
    @Bean
    @DependsOn("postgreSQLContainer")
    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgreSQLContainer.getDriverClassName());
        dataSource.setUrl(postgreSQLContainer.getJdbcUrl());
        dataSource.setUsername(postgreSQLContainer.getUsername());
        dataSource.setPassword(postgreSQLContainer.getPassword());
        return dataSource;
    }
}
