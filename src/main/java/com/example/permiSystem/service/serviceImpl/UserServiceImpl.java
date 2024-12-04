package com.example.permiSystem.service.serviceImpl;

import com.example.permiSystem.dto.CreateUserRequest;
import com.example.permiSystem.entity.Role;
import com.example.permiSystem.entity.User;
import com.example.permiSystem.entity.UserRole;
import com.example.permiSystem.repository.RoleRepo;
import com.example.permiSystem.repository.UserRepo;
import com.example.permiSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private  RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequest createUserRequest) throws Exception {
        // Check whether the user already exists
        User user1 = userRepo.findByUsername(createUserRequest.getUsername());
        Role role = roleRepo.findByRoleName(createUserRequest.getRole().toUpperCase());

        if (user1 != null) {
            // Check if the user already has the role
            boolean roleExists = user1.getUserRoles().stream()
                    .anyMatch(userRole -> userRole.getRole().equals(role));
            if (roleExists) {
                throw new Exception("User with this role already exists");
            }
        }

        // Check whether the role exists
        if (role == null) {
            throw new Exception("Role not found: " + createUserRequest.getRole());
        }

        // Create user if not already present
        User user = (user1 != null) ? user1 : new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        // Associate the user with their role
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        user.getUserRoles().add(userRole);

        userRepo.save(user);
    }

}
