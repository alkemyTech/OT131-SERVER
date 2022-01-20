package com.alkemy.ong.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.entities.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository <Organization, Long>{
	
	   public Optional<Organization> findByName(String name);

}
