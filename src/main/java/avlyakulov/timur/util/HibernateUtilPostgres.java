package avlyakulov.timur.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class HibernateUtilPostgres {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties hibernateProperty = new Properties();
            try {
                hibernateProperty.load(classLoader.getResourceAsStream("postgres.properties"));
            } catch (IOException e) {
                log.error("Error with configure file for hibernate");
            }
            sessionFactory = new Configuration()
                    .addProperties(hibernateProperty)
                    //.addAnnotatedClass(Human.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
