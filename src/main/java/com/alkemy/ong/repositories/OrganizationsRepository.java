package com.alkemy.ong.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.entities.Organizations;

@Repository
public interface OrganizationsRepository extends JpaRepository <Organizations, Long>{
	
	   public Optional<Organizations> findByName(String name);
	   public List<Organizations> findByIsActiveTrue(); /*findByActiveFalse();*/
	   
}
