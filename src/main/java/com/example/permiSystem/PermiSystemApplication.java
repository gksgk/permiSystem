package com.example.permiSystem;

import com.example.permiSystem.entity.Role;
import com.example.permiSystem.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PermiSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(PermiSystemApplication.class, args);

	}
//	@Bean
//	CommandLineRunner initRoles(RoleRepo roleRepo) {
//		return args -> {
//			Role role1 = new Role();
//			role1.setRoleName("Buyer");
//			roleRepo.save(role1);
//
//			Role role2 = new Role();
//			role2.setRoleName("Seller");
//			roleRepo.save(role2);
//
//			Role role3 = new Role();
//			role3.setRoleName("Admin");
//			roleRepo.save(role3);
//
//			System.out.println("Roles initialized successfully!");
//		};
//	}

}
