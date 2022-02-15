## Use multiple datasource in a spring-boot project

In this project we will configure and use multiple datasource

### Add datasource configs params to your application.properties file

    FIRST db config 
* spring.first.datasource.jdbcUrl=jdbc:mysql://url-to-mysql-db
* spring.first.datasource.username={username} 
* spring.first.datasource.password={password}
* spring.first.datasource.driverClassName=com.mysql.cj.jdbc.Driver
* 
###
    SECOND db config 
* spring.second.datasource.jdbcUrl=jdbc:postgresql://url-to-postgresql-db 
* spring.second.datasource.username={username} 
* spring.second.datasource.password={password}
* spring.second.datasource.driverClassName=org.postgresql.Driver

### Configuration for each datasource

#### We need a datasource bean, entity manager bean and a transaction manager bean

##### First ds config:
* Beans
```
    
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.first.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
    
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(EntityManagerFactoryBuilder builder,
                         @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(PrimaryEntity.class)
                .persistenceUnit("primaryPU")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory
                    entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

```

* Config annotations : reference the entity manager and the transaction manager as bellow.
the basePackages should point to the repository location.
```
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.tutorials.multipledatasource.primary.repo"}
)
```

##### Second ds config: do the same as the first and make sure to change the names of beans !
***Primary annotation can not be used more than one time***

### Create entities and repositories
***You should respect the specified basePackages for each datasource***
