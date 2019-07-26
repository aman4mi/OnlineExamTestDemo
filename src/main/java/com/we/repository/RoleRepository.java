package com.we.repository;

import com.we.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aman on 4/20/2019.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
