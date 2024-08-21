package com.example.demo.Repository;
import com.example.demo.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
//    UserDetails findById(Long Id);
}
