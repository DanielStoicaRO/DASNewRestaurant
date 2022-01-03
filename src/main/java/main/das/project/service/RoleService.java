package main.das.project.service;

import main.das.project.controller.exception.ResourceAlreadyExistsException;
import main.das.project.model.Role;
import main.das.project.model.RoleType;
import main.das.project.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role save(RoleType type) {
        log.info("save role {}", type);

        return (Role) roleRepository.findByRoleType(type)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("role already exists");
                })
                .orElseGet(() -> {
                    Role role = new Role(type);
                    return roleRepository.save(role);
                });
    }
}