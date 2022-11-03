package ua.vision.moiro.vision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vision.moiro.vision.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
