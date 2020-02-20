package in.spring.repo.dao.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserRole {

    @Id
    Long id;

    Role role;

    private User user;
}
