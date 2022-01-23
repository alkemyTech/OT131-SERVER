
package com.alkemy.ong.repositories;

import com.alkemy.ong.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    public Users findByEmail(String email);
    
    
}
