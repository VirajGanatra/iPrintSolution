package com.example.iPrintSolution;

import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
}
