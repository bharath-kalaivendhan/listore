package com.linkstore.lstore;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    @Bean(name = "postgreSQL")
    @Primary
    public DataSource postgreSQLDataSource() {
        // DataSourceBuilder https://docs.spring.io/spring-boot/api/java/org/springframework/boot/jdbc/DataSourceBuilder.html
        // DataSourceBuilder is utility class to create datasource object.
        // Refer base datasource class for postgresql : https://jdbc.postgresql.org/documentation/publicapi/org/postgresql/ds/common/BaseDataSource.html
        // Refer simple datasource class which does not provide connection pooling : https://jdbc.postgresql.org/documentation/publicapi/org/postgresql/ds/PGSimpleDataSource.html
        // Note: By default spring boot uses HikariDataSource
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/lstore");
        builder.password("postgres");
        builder.username("postgres");
        return builder.build();
    }
}
