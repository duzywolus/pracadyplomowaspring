package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "pacjent")
public class Pacjent {

    @Column(nullable = false, unique = true, length = 40)
    private String imiePacjent;
    @Column(nullable = false, unique = true, length = 40)
    private String nazwiskoPacjent;
    @Column(nullable = false, unique = true, length = 40)
    private String emailPacjent;
    @Column(nullable = false, unique = true, length = 64)
    private String hasloPacjent;
    @Column(nullable = false, unique = true, length = 40)
    private String telefonPacjent;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailPacjent() {
        return emailPacjent;
    }

    public void setEmailPacjent(String emailPacjent) {
        this.emailPacjent = emailPacjent;
    }

    public String getImiePacjent() {
        return imiePacjent;
    }

    public void setImiePacjent(String imiePacjent) {
        this.imiePacjent = imiePacjent;
    }

    public String getNazwiskoPacjent() {
        return nazwiskoPacjent;
    }

    public void setNazwiskoPacjent(String nazwiskoPacjent) {
        this.nazwiskoPacjent = nazwiskoPacjent;
    }

    public String getHasloPacjent() {
        return hasloPacjent;
    }

    public void setHasloPacjent(String hasloPacjent) {
        this.hasloPacjent = hasloPacjent;
    }

    public String getTelefonPacjent() {
        return telefonPacjent;
    }

    public void setTelefonPacjent(String telefonPacjent) {
        this.telefonPacjent = telefonPacjent;
    }
}
