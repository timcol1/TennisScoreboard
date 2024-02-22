package avlyakulov.timur.util;

import avlyakulov.timur.other.test.Human;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class HibernateUtilPostgres {
    public static SessionFactory getSessionFactory() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties hibernateProperty = new Properties();
        try {
            hibernateProperty.load(classLoader.getResourceAsStream("postgres.properties"));
        } catch (IOException e) {
            log.error("Error with configure file for hibernate");
        }
        return new Configuration()
                .addProperties(hibernateProperty)

                .addAnnotatedClass(Human.class)
                .buildSessionFactory();
    }
}
