package edu.arf4.trains.railwayfinal;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrainyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JtaDatabaseConfig.class);

        TrainDao trainDao = context.getBean(TrainDao.class);


        context.close();
    }
}
