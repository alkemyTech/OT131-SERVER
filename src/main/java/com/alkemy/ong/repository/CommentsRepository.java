package com.alkemy.ong.repository;

import com.alkemy.ong.model.Comments;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    
    @Query("SELECT x FROM Comments x WHERE x.news.id = :id ")
    List<Comments> getNewsAndAllComments(@Param("id") Long id);

}
