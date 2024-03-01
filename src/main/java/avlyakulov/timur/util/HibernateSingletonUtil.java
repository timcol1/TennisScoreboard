package avlyakulov.timur.util;

import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class HibernateSingletonUtil {

    private static SessionFactory sessionFactory;

    private HibernateSingletonUtil() {
    }

    public static void initSessionFactory(DatabaseType databaseType) {
        if (sessionFactory == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties hibernateProperty = new Properties();
            try {
                hibernateProperty.load(classLoader.getResourceAsStream(databaseType.getPropertyFileName()));
            } catch (IOException e) {
                log.error("Error with configure file for hibernate");
            }
            sessionFactory = new Configuration()
                    .addProperties(hibernateProperty)
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(MatchScoreModel.class)
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            log.error("You are trying to get session factory which wasn't initialized");
            throw new RuntimeException("Session factory is not created");
        } else {
            return sessionFactory;
        }
    }

    public static SessionFactory getSessionFactory(DatabaseType databaseType) {
        if (sessionFactory == null) {
            initSessionFactory(databaseType);
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}