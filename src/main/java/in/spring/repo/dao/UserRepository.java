package in.spring.repo.dao;

import in.spring.repo.dao.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByEmail(String name);

    public List<User> findAll();
}
