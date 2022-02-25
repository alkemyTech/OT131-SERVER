package com.alkemy.ong.repository;

import java.util.Optional;

import com.alkemy.ong.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findById(Long id);

    public Optional<Users> findByEmail(String email);

}
