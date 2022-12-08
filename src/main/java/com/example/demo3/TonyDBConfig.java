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
    entityManagerFactoryRef = "tonyEntityManager",
    transactionManagerRef = "tonyTransactionManager",
    basePackages = "com.example.demo3.tony"
)
public class TonyDBConfig {
    
    @Autowired
    private Environment env;

    @Bean
    public DataSource tonyDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.tony.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.tony.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.tony.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.tony.datasource.password"));
        return dataSource;
    }

    @Bean(name="tonyEntityManager")
    public LocalContainerEntityManagerFactoryBean jadenMysqlEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(tonyDataSource())
                .properties(hibernateProperties())
                   .packages(Good.class)
                   .persistenceUnit("goodPU")
                   .build();
    }

    @Bean(name="tonyTransactionManager")
    public PlatformTransactionManager jadenMysqlTransactionManager(@Qualifier("tonyEntityManager")EntityManagerFactory entityManagerFactory){
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
