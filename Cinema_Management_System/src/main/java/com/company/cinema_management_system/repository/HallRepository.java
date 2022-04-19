package com.company.cinema_management_system.repository;

import com.company.cinema_management_system.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "admin")
public interface HallRepository extends JpaRepository<Hall,Integer> {
}
