package in.spring.repo.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    Long id;
    String authority;
}
