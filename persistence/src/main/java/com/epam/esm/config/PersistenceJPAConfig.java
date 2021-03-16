package com.epam.esm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type PersistenceJPAConfig.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    private static final String DATABASE_FILE_NAME = "config.database";
    private static final String DRIVER_CLASS_NAME = "database.driverClassName";
    private static final String URL = "database.url";
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";

    @Bean
    public DataSource dataSource() {
        ResourceBundle rb = ResourceBundle.getBundle(DATABASE_FILE_NAME, new Locale(activeProfile));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(rb.getString(DRIVER_CLASS_NAME));
        dataSource.setUrl(rb.getString(URL));
        dataSource.setUsername(rb.getString(USERNAME));
        dataSource.setPassword(rb.getString(PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.epam.esm");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.jdbc.batch_size", "5");
        return properties;
    }
}
