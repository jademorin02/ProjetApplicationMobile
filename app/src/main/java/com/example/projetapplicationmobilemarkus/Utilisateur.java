package com.example.projetapplicationmobilemarkus;

public class Utilisateur {

    //INT
    protected int idUser;

    //STRING
    protected String nomUser, motDePasseUser;

    //CONSTRUCTEUR
    public Utilisateur(int idUser, String nomUser, String motDePasseUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.motDePasseUser = motDePasseUser;
    }

    //-------------------------------- ID USER --------------------------------
    //GET
    public int getIdUser() {
        return idUser;
    }

    //SET
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    //-------------------------------- NOM USER --------------------------------
    //GET
    public String getNomUser() {
        return nomUser;
    }
    //SET
    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    //-------------------------------- MOT DE PASSE USER --------------------------------
    //GET
    public String getMotDePasseUser() {
        return motDePasseUser;
    }

    //SET
     public void setMotDePasseUser(String motDePasseUser) {
            this.motDePasseUser = motDePasseUser;
        }
}









