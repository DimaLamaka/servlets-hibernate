package by.lamaka.servlets.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    long id;
    String login;
    String password;
    String description;
    Sex sex;
    Role role;
}
