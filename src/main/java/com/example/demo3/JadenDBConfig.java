package com.example.demo3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "jadenEntityManager",
    transactionManagerRef = "jadenTransactionManager",
    basePackages = "com.example.demo3"
)
public class JadenDBConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource jadenMysqlDataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.jaden.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.jaden.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.jaden.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.jaden.datasource.password"));
        return dataSource;
    }

    @Bean(name="jadenEntityManager")
    public LocalContainerEntityManagerFactoryBean jadenMysqlEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(jadenMysqlDataSource())
                .properties(hibernateProperties())
                   .packages(Good.class)
                   .persistenceUnit("goodPU")
                   .build();
    }

        @Bean(name="jadenTransactionManager")
        public PlatformTransactionManager jadenMysqlTransactionManager(@Qualifier("jadenEntityManager")EntityManagerFactory entityManagerFactory){
            return new JpaTransactionManager(entityManagerFactory);
        }

    private Map<String,?> hibernateProperties() {
        Resource resource = new ClassPathResource("hibernate.properties");
        try{
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            return properties.entrySet().stream()
                .collect(Collectors.toMap(
                    e->e.getKey().toString(),
                    e->e.getValue())
            );
        }catch(IOException e){
            return new HashMap();
        }
    }
}
