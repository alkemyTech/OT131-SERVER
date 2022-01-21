package com.alkemy.ong.repositories;

import java.util.Optional;

import com.alkemy.ong.entities.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository <Roles,Long> {
  
    public Optional<Roles> findById(Long id);
    public Optional<Roles> findByName (String name);
    
            
    
}
