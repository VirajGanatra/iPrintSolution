package com.example.iPrintSolution.objects.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUsername(String username);
    boolean existsByUsername(String username);

}
