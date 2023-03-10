package com.example.module6demo.repository;

import com.example.module6demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * from comments where house_id = :id", nativeQuery = true)
    Iterable<Comment> CommentByHouseId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select o.* from comments o join houses h on h.id = o.house_id where h.user_id= :userId limit :start , 5; ")
    Iterable<Comment> getListCommentByHouseOfUserId(@Param("userId") Long userId, @Param("start") Long start);
    @Query(value = "select * from comments join houses h on comments.house_id = h.id where is_read = true and" +
            " users_id= :userId", nativeQuery = true)
    Iterable<Comment> getAllByCommentAndIsReadTrue(@Param("userId") Long userId);

    @Query(value = "select * from comments join houses h on comments.house_id = h.id where is_read = false and" +
            " h.user_id= :userId", nativeQuery = true)
    Iterable<Comment> getAllByCommentAndIsReadFalse(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select * from comments where house_id = :id limit :start , 5")
    Iterable<Comment> getCommentByHouseId(@Param("id") Long houseId, @Param("start") long start);

}
