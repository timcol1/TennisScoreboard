package avlyakulov.timur.util;

import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class HibernateUtilH2 {

    private static SessionFactory sessionFactory;


    //todo when the program finished close session factory
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties hibernateProperty = new Properties();
            try {
                hibernateProperty.load(classLoader.getResourceAsStream("h2.properties"));
            } catch (IOException e) {
                log.error("Error with configure file for hibernate");
            }
            sessionFactory = new Configuration()
                    .addProperties(hibernateProperty)
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(MatchScoreModel.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}