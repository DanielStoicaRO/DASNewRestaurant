package main.das.project.config;

import main.das.project.controller.exception.ResourceAlreadyExistsException;
import main.das.project.model.*;
import main.das.project.repository.PrivilegeRepository;
import main.das.project.repository.RoleRepository;
import main.das.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class DbInit {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;


    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            log.info("setup initial data");

            // create privileges
            Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE);
            Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE);

            // create roles
            createRoleIfNotFound(RoleType.ADMIN, Set.of(readPrivilege, writePrivilege));
            createRoleIfNotFound(RoleType.USER, Set.of(readPrivilege, writePrivilege));

            // create main admin, admin, user
            User mainAdmin = createMainAdmin();
            userRepository.save(mainAdmin);

            User admin = createAdmin();
            userRepository.save(admin);

            User user = createUser();
            userRepository.save(user);

        };
    }


    private User createMainAdmin() {
        User admin = new User(
                "main@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "jon",
                "snow");
        Role adminRole = roleRepository.findByRoleType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        return admin;
    }

    private User createAdmin() {
        User admin = new User(
                "admin@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "bill",
                "clinton");
        Role adminRole = roleRepository.findByRoleType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        return admin;
    }

    private User createUser() {
        User user = new User(
                "user@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "alex",
                "vasile");

        Role userRole = roleRepository.findByRoleType(RoleType.USER).orElseThrow();
        user.addRole(userRole);
        return user;
    }


    @Transactional
    Role createRoleIfNotFound(RoleType type, Set<Privilege> privileges) {
        return (Role) roleRepository.findByRoleType(type)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("role already exists");
                })
                .orElseGet(() -> {
                    Role role = new Role(type);
                    role.setPrivileges(privileges);
                    return roleRepository.save(role);
                });
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(PrivilegeType name) {
        return (Privilege) privilegeRepository.findByType(name)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("privilege already exists");
                })
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }


}