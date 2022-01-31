package com.alkemy.ong.util;

import com.alkemy.ong.model.Members;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.model.Roles;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RolesRepository;
import com.alkemy.ong.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeed implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        loadUserData();
        loadOrganizationsData();
    }

    private void loadUserData() {

        if (usersRepository.count() == 0) {

            Users admin = new Users(
                    "Admin", "Admin",
                    "admin@ong.com", passwordEncoder.encode("12345678")
            );
            Roles roleAdmin = new Roles(
                    RoleName.ROLE_ADMIN, "Admin");

            rolesRepository.save(roleAdmin);
            admin.setRole(roleAdmin);
            usersRepository.save(admin);

            Users user = new Users(
                    "User", "User",
                    "user@ong.com", passwordEncoder.encode("12345678")
            );
            Roles roleUser = new Roles(
                    RoleName.ROLE_USER, "User"
            );

            rolesRepository.save(roleUser);
            user.setRole(roleUser);
            usersRepository.save(user);
        }
    }

    private void loadOrganizationsData() {

        Organizations org1 = new Organizations();

    }

}
