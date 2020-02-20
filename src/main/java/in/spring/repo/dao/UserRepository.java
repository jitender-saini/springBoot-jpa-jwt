package in.spring.repo.dao;

import in.spring.repo.dao.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByName(String name);

    public User findByUsername(String name);
}
