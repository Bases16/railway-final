package edu.arf4.trains.railwayfinal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableTransactionManagement
//@PropertySource(value = "classpath:testdatabase.properties")

//@ComponentScan("edu.arf4.trains.railwayfinal") //for testing
public class TestDatabaseConfig extends DatabaseConfig {
}
