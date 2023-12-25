package com.faculty.demo.todo;

import com.faculty.demo.user.User;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

//    @Query("SELECT * FROM Todo t WHERE t.user.id = ?1")
//    @Transactional
//    @Modifying
//    @Query("from todo e where e.fulfillmentId in ?1")
    @Query(value = "SELECT * FROM Todo t WHERE t.user_id = ?1", nativeQuery = true)
    Iterable<Todo> findAllByUser(Long userId);
}
