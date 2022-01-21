
package com.alkemy.ong.repositories;

import com.alkemy.ong.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    
    public Users findByEmail(String email);
    
}
