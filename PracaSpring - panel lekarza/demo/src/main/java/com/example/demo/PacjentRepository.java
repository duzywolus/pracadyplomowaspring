package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacjentRepository extends JpaRepository<Pacjent, Integer> {
    @Query("select u from Pacjent u where u.emailPacjent = :emailPacjent")
    Pacjent findByEmail(String emailPacjent);

}
