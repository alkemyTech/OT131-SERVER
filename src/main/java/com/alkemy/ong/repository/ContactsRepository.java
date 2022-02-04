package com.alkemy.ong.repository;

import com.alkemy.ong.model.Contacts;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Optional<Contacts> findByEmail(String email);

    List<Contacts> findByisActiveTrue();
}
