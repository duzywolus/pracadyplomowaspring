package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "lekarzspring")
public class Lekarz {

    @Column(nullable = false, unique = false, length = 40)
    private String imieLekarz;
    @Column(nullable = false, unique = false, length = 40)
    private String nazwiskoLekarz;
    @Column(nullable = false, unique = true, length = 40)
    private String emailLekarz;
    @Column(nullable = false, unique = false, length = 64)
    private String hasloLekarz;
    @Column(nullable = false, unique = true, length = 40)
    private String telefonLekarz;
    @Column(nullable = false, unique = false, length = 40)
    private String specjalizacja;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getImieLekarz() {
        return imieLekarz;
    }

    public void setImieLekarz(String imieLekarz) {
        this.imieLekarz = imieLekarz;
    }

    public String getNazwiskoLekarz() {
        return nazwiskoLekarz;
    }

    public void setNazwiskoLekarz(String nazwiskoLekarz) {
        this.nazwiskoLekarz = nazwiskoLekarz;
    }

    public String getEmailLekarz() {
        return emailLekarz;
    }

    public void setEmailLekarz(String emailLekarz) {
        this.emailLekarz = emailLekarz;
    }

    public String getHasloLekarz() {
        return hasloLekarz;
    }

    public void setHasloLekarz(String hasloLekarz) {
        this.hasloLekarz = hasloLekarz;
    }

    public String getTelefonLekarz() {
        return telefonLekarz;
    }

    public void setTelefonLekarz(String telefonLekarz) {
        this.telefonLekarz = telefonLekarz;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
