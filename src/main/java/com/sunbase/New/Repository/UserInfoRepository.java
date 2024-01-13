package com.sunbase.New.Repository;

import com.sunbase.New.Entity.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String username);
    List<UserInfo> findAll(); // Optional method for fetching all customers

    Optional<UserInfo> findById(Long id);

    void deleteById(Long id);

    @Query("SELECT u FROM UserInfo u WHERE u.firstName LIKE %:firstName% OR u.lastName LIKE %:lastName%")
    List<UserInfo> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable);

}