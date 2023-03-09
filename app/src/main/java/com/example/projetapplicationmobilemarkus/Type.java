package com.example.projetapplicationmobilemarkus;

import com.google.gson.annotations.SerializedName;

public class Type {

    //INT
    protected int idType;

    //STRING
    protected String nomType;

    //CONSTRUCTEUR
    public Type(int idType, String nomType)
    {
        this.idType = idType;
        this.nomType = nomType;
    }

    //-------------------------------- ID TYPE --------------------------------
    //GET
    public int getIdType() {
        return idType;
    }

    //SET
    public void setIdType(int idType) {
        this.idType = idType;
    }


    //-------------------------------- NOM TYPE --------------------------------
    //GET
    public String getNomType() {
        return nomType;
    }

    //SET
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

}
