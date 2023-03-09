package com.example.projetapplicationmobilemarkus;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Motif {

    //INT
    protected int idMotif, idType, idUser;

    //STRING
    protected String source, nomMotif, imgCreation, dateCreation;

    public Motif(int idMotif, int idType, int idUser, String dateCreation, String source, String nomMotif, String imgCreation) {
        this.idMotif = idMotif;
        this.idType = idType;
        this.idUser = idUser;
        this.dateCreation = dateCreation;
        this.source = source;
        this.nomMotif = nomMotif;
        this.imgCreation = imgCreation;
    }

    //-------------------------------- ID MOTIF --------------------------------
    //GET
    public int getIdMotif() {
        return idMotif;
    }

    //SET
    public void setIdMotif (int idMotif)
    {
        this.idMotif = idMotif;
    }


    //-------------------------------- ID TYPE --------------------------------
    //GET
    public int getIdType() {
        return idType;
    }

    //SET
    public void setIdType (int idType)
    {
        this.idType = idType;
    }


    //-------------------------------- ID USER --------------------------------
    //GET
    public int getIdUser(){return idUser;}

    //SET
    public void  setIdUser(int idUser)
    {
        this.idUser = idUser;
    }


    //-------------------------------- SOURCE --------------------------------
    //GET
    public String getSource() {
        return source;
    }

    //SET
    public void setSource(String setSource)
    {
        this.source = source;
    }


    //-------------------------------- DATE CREATION --------------------------------
    //GET
    public String getDateCreation() {
        return dateCreation;
    }

    //SET
    public void setDateCreation(String dateCreation)
    {
        this.dateCreation = dateCreation;
    }


    //-------------------------------- NOM MOTIF --------------------------------
    //GET
    public String getNomMotif() {
        return nomMotif;
    }

    //SET
    public void setNomMotif(String nomMotif) {
        this.nomMotif = nomMotif;
    }


    //-------------------------------- IMAGE CREATION --------------------------------
    //GET
    public String getImgCreation() {
        return imgCreation;
    }

    //SET
    public void setImgCreation(String imgCreation) {
        this.imgCreation = imgCreation;
    }
}
