package by.lamaka.hibernate.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "region")
    String region;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Car> cars;
}
