package br.com.labs.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer {

    private static final String IMAGE = "postgres:14";
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(IMAGE)
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        POSTGRES_CONTAINER.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return POSTGRES_CONTAINER;
    }
}
