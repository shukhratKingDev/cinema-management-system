package com.company.cinema_management_system.repository;

import com.company.cinema_management_system.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    Optional<Manager> findByUsername(String username);
    boolean existsByUsername(String username);
}
