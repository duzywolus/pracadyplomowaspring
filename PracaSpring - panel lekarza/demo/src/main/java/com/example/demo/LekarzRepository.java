package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LekarzRepository extends JpaRepository<Lekarz, Integer> {
    @Query("select u from Lekarz u where u.emailLekarz =?1")
    Lekarz findByEmail(String emailLekarz);

}
