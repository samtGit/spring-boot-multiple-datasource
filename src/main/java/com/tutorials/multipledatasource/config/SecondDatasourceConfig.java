package com.tutorials.multipledatasource.config;

import com.tutorials.multipledatasource.second.domain.SecondEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"com.tutorials.multipledatasource.second.repo"}
)
public class SecondDatasourceConfig {

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(SecondEntity.class)
                .persistenceUnit("secondPU")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory
                    entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
