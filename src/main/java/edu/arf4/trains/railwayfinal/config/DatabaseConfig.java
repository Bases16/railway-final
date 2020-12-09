package edu.arf4.trains.railwayfinal.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement

//for testing
@ComponentScan(value = { "edu.arf4.trains.railwayfinal.dao",
                         "edu.arf4.trains.railwayfinal.model",
                         "edu.arf4.trains.railwayfinal.service"  }, lazyInit = true
)
public class DatabaseConfig {

    private final Properties hibernateJpaProperties;
    private final Properties dataSourceProperties;

    public DatabaseConfig() {

        Properties hibernateJpaProperties = new Properties();
        Properties datasourceProperties = new Properties();
        try {
            File hibernateJpaResource = new ClassPathResource("hibernateJpa.properties").getFile();
            File datasourceResource = new ClassPathResource("datasource.properties").getFile();
            hibernateJpaProperties.load(new FileReader(hibernateJpaResource));
            datasourceProperties.load(new FileReader(datasourceResource));
            this.hibernateJpaProperties = hibernateJpaProperties;
            this.dataSourceProperties = datasourceProperties;

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("couldn't have loaded persistence properties");
        }
    }


    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean xaDataSource() {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSourceClassName(dataSourceProperties.getProperty("datasource.xa_datasource_class_name"));

        Properties xaProperties = new Properties();
        xaProperties.put("user", dataSourceProperties.getProperty("datasource.username"));
        xaProperties.put("password", dataSourceProperties.getProperty("datasource.password"));
        xaProperties.put("URL", dataSourceProperties.getProperty("datasource.url"));

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
        factoryBean.setJpaProperties(hibernateJpaProperties);

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
