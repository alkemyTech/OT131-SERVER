package com.alkemy.ong.repository;

import java.util.List;
import java.util.Optional;

import com.alkemy.ong.model.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query ("SELECT c.body FROM Comments c ORDER BY date_created ASC")
    public Optional <List<String>> listComments ();
}
