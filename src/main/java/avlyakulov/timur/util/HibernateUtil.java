package avlyakulov.timur.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    public static SessionFactory getSessionFactory(Class<?> clazz) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Properties hibernateProperty = new Properties();
        hibernateProperty.load(classLoader.getResourceAsStream("postgres.properties"));

        return new Configuration().addAnnotatedClass(clazz).addProperties(hibernateProperty).buildSessionFactory();
    }
}