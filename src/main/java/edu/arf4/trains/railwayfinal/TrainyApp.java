package edu.arf4.trains.railwayfinal;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.implementations.StationDaoImpl;
import edu.arf4.trains.railwayfinal.dao.interfaces.StationDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrainyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);

        StationDao stationDao = context.getBean(StationDaoImpl.class);

        context.close();
    }
}
