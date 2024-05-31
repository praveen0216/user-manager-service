package com.management.user;

import com.management.user.config.TestContainerConfig;
import com.management.user.config.TestDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

@SpringBootTest
@ContextConfiguration(classes = {TestContainerConfig.class, TestDatabaseConfig.class})
class TestApplication {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

}