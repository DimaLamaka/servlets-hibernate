package by.lamaka.hibernate.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "car_number", unique = true, nullable = false)
    int carNumber;

    @Column(name = "car_brand")
    @Enumerated(EnumType.STRING)
    CarBrand carBrand;

    @Column(name = "date_of_manufacture_car")
    @Temporal(TemporalType.DATE)
    Date dateOfManufactureCar;

    @Column(name = "time_of_create")
    @Temporal(TemporalType.TIME)
    @CreationTimestamp
    Date timeOfCreate;

    @UpdateTimestamp
    @Column(name = "time_of_update")
    @Temporal(TemporalType.TIME)
    Date timeOfUpdate;

    @Column(name = "is_exist_on_warehouse")
    boolean isExistOnWarehouse;

    @Column(name = "version")
    @Version
    int version;


    public Car(int carNumber, CarBrand carBrand, Date dateOfManufactureCar, boolean isExistOnWarehouse) {
        this.carNumber = carNumber;
        this.carBrand = carBrand;
        this.dateOfManufactureCar = dateOfManufactureCar;
        this.isExistOnWarehouse = isExistOnWarehouse;
    }
}
