package das.project.repository;

import das.project.modelEntity.Role;
import das.project.modelEntity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType roleType);

    // select entity join with collection
    @Query("FROM Role r join r.users u WHERE u.id = :userId")
    Set<Role> getRoles(Integer userId);


}
