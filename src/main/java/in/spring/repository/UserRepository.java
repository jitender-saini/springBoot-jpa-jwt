package in.spring.repository;

import in.spring.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String name);

    List<User> findAll();
}
