package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "wizytyspring")
public class Wizyty {


    @Column(nullable = true, length = 40)
    private String termin;
    @Column(nullable = true, length = 40)
    private String godzina;
    @Column(nullable = true, length = 40)
    private String lekarz;
    @Column(nullable = true, length = 40)
    private String rodzaj;
    @Column(length = 40)
    private String pacjent;
    @Column(length = 40)
    private String status = "utworzona";
    @Column(length = 50, nullable = true)
    private String symptomy = "brak symtpomow";
    @Column(length = 50)
    private String diagnoza;
    @Column(length = 300)
    private String zalecenia;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public String getLekarz() {
        return lekarz;
    }

    public void setLekarz(String lekarz) {
        this.lekarz = lekarz;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getPacjent() {
        return pacjent;
    }

    public void setPacjent(String pacjent) {
        this.pacjent = pacjent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptomy() {
        return symptomy;
    }

    public void setSymptomy(String symptomy) {
        this.symptomy = symptomy;
    }

    public String getDiagnoza() {
        return diagnoza;
    }

    public void setDiagnoza(String diagnoza) {
        this.diagnoza = diagnoza;
    }

    public String getZalecenia() {
        return zalecenia;
    }

    public void setZalecenia(String zalecenia) {
        this.zalecenia = zalecenia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wizyty() {
    }

    public Wizyty(String termin, String godzina, String lekarz, String rodzaj, String pacjent, String status, String symptomy, String diagnoza, String zalecenia, Integer id) {
        this.termin = termin;
        this.godzina = godzina;
        this.lekarz = lekarz;
        this.rodzaj = rodzaj;
        this.pacjent = pacjent;
        this.status = status;
        this.symptomy = symptomy;
        this.diagnoza = diagnoza;
        this.zalecenia = zalecenia;
        this.id = id;
    }
}
