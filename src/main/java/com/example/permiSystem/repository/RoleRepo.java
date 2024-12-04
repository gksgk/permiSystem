package com.example.permiSystem.repository;

import com.example.permiSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {

    Role findByRoleName(String upperCase);
}
