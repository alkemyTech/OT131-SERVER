package com.alkemy.ong.repository;

import com.alkemy.ong.model.Members;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("membersRepository")
public interface MembersRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByFacebookUrl(String facebookUrl);

    Optional<Members> findByLinkedinUrl(String linkedinUrl);

    Optional<Members> findByInstagramUrl(String instagramUrl);
}
