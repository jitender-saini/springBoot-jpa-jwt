package in.spring.repo.dao.repository;

import in.spring.repo.dao.domain.Role;
import in.spring.repo.dao.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(String role);
}
