package com.example.cdwebbe.repository;

import com.example.cdwebbe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findOnedById(Long id);
    User findOneByEmail(String email);

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ' ', u.address, ' ', u.email, ' ', u.gender, ' ', u.phone,' ', u.createdAt ) LIKE %?1%")
    Page<User> findAllSearch(String search, Pageable pageable);
    @Query("SELECT COUNT (u) FROM User u WHERE CONCAT(u.name, ' ', u.address, ' ', u.email, ' ', u.gender, ' ', u.phone,' ', u.createdAt) LIKE %?1%")
    int countBySearch(String search);

    public void deleteById(Long id);

    public Page<User> findAllByNameContainsOrAddressContainsOrEmailContainsOrGenderContainsOrPhoneContains(String name, String address, String email, String gender, String phone, Pageable pageable);
    public int countByNameContainsOrAddressContainsOrEmailContainsOrGenderContainsOrPhoneContains(String name, String address, String email, String gender, String phone);
}
