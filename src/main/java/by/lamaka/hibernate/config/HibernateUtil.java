package by.lamaka.hibernate.config;

import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;
import by.lamaka.hibernate.entity.Customer;
import by.lamaka.hibernate.entity.Region;
import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class HibernateUtil {
    static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            EntityScanner.scanPackages("by/lamaka/hibernate/entity").addTo(configuration);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    @SneakyThrows
    public static void saveData() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Car car = Car.builder()
                    .carNumber(1234)
                    .carBrand(CarBrand.TESLA)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-24"))
                    .isExistOnWarehouse(true)
                    .build();
            Car car1 = Car.builder()
                    .carNumber(1261)
                    .carBrand(CarBrand.AUDI)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("1996-06-23"))
                    .isExistOnWarehouse(true)
                    .build();
            Car car2 = Car.builder()
                    .carNumber(0000)
                    .carBrand(CarBrand.FORD)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2004-05-13"))
                    .isExistOnWarehouse(true)
                    .build();
            Car car3 = Car.builder()
                    .carNumber(1999)
                    .carBrand(CarBrand.BMW)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2003-05-03"))
                    .isExistOnWarehouse(true)
                    .build();
            Car car4 = Car.builder()
                    .carNumber(1201)
                    .carBrand(CarBrand.MERCEDES)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-13"))
                    .isExistOnWarehouse(true)
                    .build();
            Car car5 = Car.builder()
                    .carNumber(2451)
                    .carBrand(CarBrand.MERCEDES)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-13"))
                    .isExistOnWarehouse(true)
                    .build();

            Car car6 = Car.builder()
                    .carNumber(7324)
                    .carBrand(CarBrand.LEXUS)
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-03"))
                    .isExistOnWarehouse(true)
                    .build();


            Region region = Region.builder().region("Minsk").cars(Arrays.asList(car1, car5)).build();
            Region region1 = Region.builder().region("Mogilev").cars(Arrays.asList(car2, car, car1)).build();
            Region region2 = Region.builder().region("Gomel").cars(Arrays.asList(car3, car)).build();
            Region region3 = Region.builder().region("Vitebsk").cars(Arrays.asList(car4, car5)).build();
            Region region4 = Region.builder().region("Brest").cars(Arrays.asList(car6, car4)).build();

            car.setRegions(Arrays.asList(region1, region2));
            car1.setRegions(Arrays.asList(region1, region));
            car2.setRegions(Arrays.asList(region1));
            car3.setRegions(Arrays.asList(region2));
            car4.setRegions(Arrays.asList(region3, region4));
            car5.setRegions(Arrays.asList(region, region3));
            car6.setRegions(Arrays.asList(region4));

            Customer customer = Customer.builder()
                    .firstName("Evgeniy")
                    .secondName("Semenov")
                    .age(22)
                    .cars(Arrays.asList(car1, car5))
                    .build();

            Customer customer1 = Customer.builder()
                    .firstName("Dmitry")
                    .secondName("Egorov")
                    .age(18)
                    .cars(Arrays.asList(car, car2))
                    .build();

            Customer customer2 = Customer.builder()
                    .firstName("Alina")
                    .secondName("Grisheva")
                    .age(27)
                    .cars(Arrays.asList(car4, car6))
                    .build();
            car1.setCustomer(customer);
            car5.setCustomer(customer);
            car.setCustomer(customer1);
            car2.setCustomer(customer1);
            car4.setCustomer(customer2);
            car6.setCustomer(customer2);

            session.saveOrUpdate(customer);
            session.saveOrUpdate(customer1);
            session.saveOrUpdate(customer2);

            transaction.commit();
        }
    }

}
