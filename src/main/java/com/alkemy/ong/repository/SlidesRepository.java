package com.alkemy.ong.repository;

import java.util.Optional;
import com.alkemy.ong.model.Slides;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {

    @Query("SELECT c FROM Slides c WHERE organization_id = :idOrg")
    public Optional<List<Slides>> findByOrganizationId(@Param("idOrg") Long id);

    public List<Slides> findByOrderByOrderAsc();

}
