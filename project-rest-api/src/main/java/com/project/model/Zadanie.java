package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="zadanie")
public class Zadanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="zadanie_id")
    private Integer zadanieId;

    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 50)
    private String nazwa;

    private Integer kolejnosc;

    @Size(max = 1000, message = "opis musi zawierać do {max} znaków!")
    @Column( length = 50)
    private String opis;

    @CreationTimestamp
    @NotBlank(message = "Pole data oddania nie może być puste!")
    @Column(name = "dataczas_oddania", nullable = false, updatable = false)
    private LocalDateTime dataCzasOddania;

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Projekt projekt;

    public Zadanie() {
    }

    public Integer getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(Integer zadanieId) {
        this.zadanieId = zadanieId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataCzasOddania() {
        return dataCzasOddania;
    }

    public void setDataCzasOddania(LocalDateTime dataCzasOddania) {
        this.dataCzasOddania = dataCzasOddania;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }
}
