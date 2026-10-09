package org.ecole.attestationscolaire.domain;

public class Etudiant {
    private Long id;
    private String nom;
    private String filiere;

    public Etudiant() {
    }

    public Etudiant(Long id, String nom, String filiere) {
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
