package com.example.projetapplicationmobilemarkus;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Score {

    //INT
    protected int idScore, idMotifScore;

    //STRING
    protected String dateScore;

    //CONSTRUCTEUR
    public Score(int idScore, int idMotifScore, String dateScore)
    {
        this.idScore = idScore;
        this.idMotifScore = idMotifScore;
        this.dateScore = dateScore;
    }

    //-------------------------------- ID SCORE --------------------------------
    //GET
    public int getIdScore() {
        return idScore;
    }

    //SET
    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }


    //-------------------------------- ID MOTIF SCORE --------------------------------
    //GET
    public int getIdMotifScore() {
        return idMotifScore;
    }

    //SET
    public void setIdMotifScore(int idMotifScore) {
        this.idMotifScore = idMotifScore;
    }


    //-------------------------------- DATE SCORE --------------------------------
    //GET
    public String getDateScore()
    {
        return dateScore;
    }

    //SET
    public void setDateScore(String dateScore) {
        this.dateScore = dateScore;
    }






}
