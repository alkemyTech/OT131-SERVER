package com.alkemy.ong.repository;

import com.alkemy.ong.model.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

    public List<Activities> findByIsActiveTrue();

    public Optional<Activities> findByName(String name);
}
