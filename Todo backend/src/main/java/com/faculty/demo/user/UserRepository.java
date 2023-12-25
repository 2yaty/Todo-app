package com.faculty.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="SELECT * FROM User u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
