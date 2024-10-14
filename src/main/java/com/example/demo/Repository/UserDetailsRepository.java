package com.example.demo.Repository;
import com.example.demo.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    List<UserDetails> findByUserId(Long userId);

    void deleteByUserId(Long userId);
//    UserDetails findById(Long Id);
}
