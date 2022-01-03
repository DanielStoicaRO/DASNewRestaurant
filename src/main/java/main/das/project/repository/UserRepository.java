package main.das.project.repository;

import main.das.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Integer id);
}
