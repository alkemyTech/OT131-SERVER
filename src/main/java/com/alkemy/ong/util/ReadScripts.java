package com.alkemy.ong.util;

import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRepository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReadScripts {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    public void dataSourceInitializer(Resource data) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator(data));
        dataSourceInitializer.afterPropertiesSet();
    }

    private DatabasePopulator databasePopulator(Resource data) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(data);
        return populator;
    }

    public void encodePasswordPopulator() {
        List<Users> users = usersRepository.findAll();
        users.stream().map(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }).forEachOrdered(user -> {
            usersRepository.save(user);
        });
    }

}
