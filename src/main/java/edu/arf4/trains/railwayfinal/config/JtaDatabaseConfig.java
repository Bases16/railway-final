package edu.arf4.trains.railwayfinal.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:jta_database.properties")

@ComponentScan(value = "edu.arf4.trains.railwayfinal", lazyInit = true) //for testing
public class JtaDatabaseConfig {

    private Environment env;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.connection.CharSet", env.getRequiredProperty("hibernate.connection.CharSet"));
        properties.put("hibernate.connection.characterEncoding", env.getRequiredProperty("hibernate.connection.characterEncoding"));
        properties.put("hibernate.connection.useUnicode", env.getRequiredProperty("hibernate.connection.useUnicode"));
        properties.put("hibernate.physical_naming_strategy", env.getRequiredProperty("hibernate.physical_naming_strategy"));
//        properties.put("hibernate.hbm2ddl.import_files", env.getRequiredProperty("hibernate.hbm2ddl.import_files"));
//        properties.put("hibernate.hbm2ddl.import_files_sql_extractor", env.getRequiredProperty("hibernate.hbm2ddl.import_files_sql_extractor"));
        properties.put("javax.persistence.validation.mode", env.getRequiredProperty("javax.persistence.validation.mode"));

        properties.put("hibernate.connection.handling_mode", env.getRequiredProperty("hibernate.connection.handling_mode"));
        properties.put("hibernate.current_session_context_class", env.getRequiredProperty("hibernate.current_session_context_class"));
        properties.put("javax.persistence.transactionType", env.getRequiredProperty("javax.persistence.transactionType"));
        properties.put("hibernate.transaction.jta.platform", env.getRequiredProperty("hibernate.transaction.jta.platform"));

        return properties;
    }


    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean xaDataSource() {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSourceClassName(env.getRequiredProperty("datasource.xa_datasource_class_name"));

        Properties xaProperties = new Properties();
        xaProperties.put("user", env.getRequiredProperty("datasource.username"));
        xaProperties.put("password", env.getRequiredProperty("datasource.password"));
        xaProperties.put("URL", env.getRequiredProperty("datasource.url"));
        xaDataSource.setXaProperties(xaProperties);
        xaDataSource.setUniqueResourceName("myXADataSource");
        xaDataSource.setPoolSize(5);
        xaDataSource.setLocalTransactionMode(true);
        return xaDataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(AtomikosDataSourceBean xaDataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(xaDataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("edu.arf4.trains.railwayfinal.model");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public JtaTransactionManager transactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setTransactionManager(userTransactionManager());
        transactionManager.setUserTransaction(userTransactionManager());
        return transactionManager;
    }


}
