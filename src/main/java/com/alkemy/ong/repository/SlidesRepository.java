package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slides;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {
	
	public List<Slides> findByOrderByOrderAsc();

}
