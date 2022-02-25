package com.alkemy.ong.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.model.Organizations;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Long> {

    public Optional<Organizations> findById(Long id);

    public Optional<Organizations> findByName(String name);

    public List<Organizations> findByIsActiveTrue();

}
