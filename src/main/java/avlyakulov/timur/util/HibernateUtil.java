package avlyakulov.timur.util;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.Player;
import avlyakulov.timur.other.model.Person;
import avlyakulov.timur.other.model.PersonTask;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties hibernateProperty = new Properties();
        //hibernateProperty.load(classLoader.getResourceAsStream("postgres.properties"));
        hibernateProperty.load(classLoader.getResourceAsStream("h2.properties"));

        return new Configuration()
                .addProperties(hibernateProperty)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(PersonTask.class)
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .buildSessionFactory();
    }
}