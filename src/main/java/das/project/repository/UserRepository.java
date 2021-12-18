package das.project.repository;


import das.project.modelEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Integer id);

}
