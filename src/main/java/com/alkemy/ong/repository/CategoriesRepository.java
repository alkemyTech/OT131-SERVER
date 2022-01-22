package com.alkemy.ong.repository;

import com.alkemy.ong.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    public Optional<Categories> findByName(String name);

    public List<Categories> findByIsActivatedTrue();
}
