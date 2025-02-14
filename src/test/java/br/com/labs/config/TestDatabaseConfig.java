package br.com.labs.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;

@TestConfiguration
public class TestDatabaseConfig {

    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(PostgresTestContainer.getInstance().getHost())
                .port(PostgresTestContainer.getInstance().getMappedPort(5432))
                .database(PostgresTestContainer.getInstance().getDatabaseName())
                .username(PostgresTestContainer.getInstance().getUsername())
                .password(PostgresTestContainer.getInstance().getPassword())
                .build());
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
}
