package com.problem.two.liquibases;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.problem.two.liquibases.entity.a",
    entityManagerFactoryRef = "aEntityManager",
    transactionManagerRef = "aTransactionManager"
)
public class AConfiguration {

    private final Environment env;

    @Autowired
    public AConfiguration(final Environment env) {
        this.env = env;
    }

    @Bean
    @ConfigurationProperties(prefix = "a.datasource")
    public DataSource aDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean aEntityManager() {
        final LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(aDataSource());
        em.setPackagesToScan("com.problem.two.liquibases.entity.a");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<>();
        em.setJpaPropertyMap(properties);
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        return em;
    }

    @Bean
    public PlatformTransactionManager aTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                aEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @ConfigurationProperties(prefix = "a.liquibase")
    public LiquibaseProperties aLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean("liquibaseA")
    //@DependsOn("liquibaseB") erronous case -> findEnityB test would fail because B variant will be created first then dropped by the variant A execution (drop-first: true)
    public SpringLiquibase aLiquibase() {
        return SpringLiqiubaseUtils.springLiquibase(aDataSource(), aLiquibaseProperties());
    }
}
