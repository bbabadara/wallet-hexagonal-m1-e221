package org.ecole.attestationscolaire.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class EtudiantEntity {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String filiere;

    public EtudiantEntity() {
    }

    public EtudiantEntity(Long id, String nom, String filiere) {
        this.id = id;
        this.nom = nom;
        this.filiere = filiere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }
}
