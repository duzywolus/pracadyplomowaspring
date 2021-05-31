package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WizytyRepository extends JpaRepository<Wizyty, Integer> {
    @Query("select u from Wizyty u where u.lekarz = :lekarz")
    List<Wizyty> findAllByEmail(String lekarz);

    @Query("select u from Wizyty u where u.status = 'zarezerwowana' and u.lekarz = :lekarz")
    List<Wizyty> findByStatus(String lekarz);

    @Query(value = "select u from Wizyty u where u.pacjent like %:pacjent% and u.status = 'zakonczona'")
    List<Wizyty> patientHistory(@Param("pacjent") String pacjent);

    @Query(value = "select u from Wizyty u where u.lekarz =:lekarz and u.status = 'zakonczona'")
    List<Wizyty> findYourVisitHistory(String lekarz);

    @Query(value = "select u from Wizyty u where u.lekarz =:lekarz and (u.status ='utworzona' or " +
            "u.status ='zarezerwowana')")
    List<Wizyty> findYourCreatedVisit(String lekarz);


    /*@Transactional
    @Modifying
    @Query(value = "update Wizyty w set w.status='zarezerwowana', w.pacjent = :pacjent where w.id =:id")
    void rezerwowanieWizyty(Integer id, String pacjent);*/

    @Transactional
    @Modifying
    @Query(value = "update Wizyty w set w.status='zakonczona',  w.symptomy =:symptomy, w.diagnoza= :diagnoza, " +
            "w.zalecenia=:zalecenia where w.id =:id")
    void zakonczenieWizyty(@Param("symptomy") String symptomy, @Param("diagnoza") String diagnoza,
                           @Param("zalecenia") String zalecenia, Integer id);

    Optional<Wizyty> findWizytaById(Integer id);
}
