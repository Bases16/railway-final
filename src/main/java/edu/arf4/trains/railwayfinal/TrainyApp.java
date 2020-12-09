package edu.arf4.trains.railwayfinal;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrainyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);

        TrainDao trainDao = context.getBean(TrainDao.class);


        context.close();
    }
}
