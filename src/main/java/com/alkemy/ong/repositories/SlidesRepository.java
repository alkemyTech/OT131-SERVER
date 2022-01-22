package com.alkemy.ong.repositories;

import com.alkemy.ong.entities.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long>{
    
}
