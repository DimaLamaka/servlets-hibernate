package by.lamaka.hibernate.config;

import by.lamaka.hibernate.entity.Car;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class HibernateUtil {
    static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Car.class);
                sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}
