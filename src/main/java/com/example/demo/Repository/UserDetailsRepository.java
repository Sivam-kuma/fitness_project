package com.example.demo.Repository;
import com.example.demo.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    @Query("SELECT u FROM UserDetails u WHERE u.userId = :userId")
    Optional<UserDetails> findByUserId(@Param("userId") Long userId);
    Optional<UserDetails> findById(Long id);
    void deleteByUserId(Long userId);
//    UserDetails findById(Long Id);
}
