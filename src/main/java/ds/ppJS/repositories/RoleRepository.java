package ds.PP3_1_2SS.repositories;

import ds.PP3_1_2SS.models.Role;
import ds.PP3_1_2SS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
Optional<Role> findByRoleName(String roleName);
}
