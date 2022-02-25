package com.alkemy.ong.repository;

import java.util.Optional;
import com.alkemy.ong.model.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("SELECT x FROM Comments x WHERE x.news.id = :id ORDER BY date_created ASC")
    List<Comments> getNewsAndAllComments(@Param("id") Long id);

    @Query("SELECT c.body FROM Comments c ORDER BY date_created ASC")
    public Optional<List<String>> listComments();

}
