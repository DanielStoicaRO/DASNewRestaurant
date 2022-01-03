package main.das.project.repository;

import main.das.project.model.Privilege;
import main.das.project.model.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByType(PrivilegeType type);
}
