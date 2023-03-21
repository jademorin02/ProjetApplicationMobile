package com.example.projetapplicationmobilemarkus;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceServeur {

    //AFFICHER LES TYPES ---------------------------------------------
    @GET("/index.php")
    Call<ResponseBody>getType();

    ////AFFICHER LES MOTIFS ---------------------------------------------
    @GET("index.php")
    Call<ResponseBody> getMotifQuery();

    //AFFICHER LES SCORES ---------------------------------------------
    @GET("index.php")
    Call<ResponseBody> getScore(@Query("motif") int idMotif);

    //AFFICHER LES UTILISATEUR
    @POST("connexion.php")
    @FormUrlEncoded
    Call<ResponseBody> getUser(
            @Field("idUser") int idUser,
            @Field("nomUser") String nomUser,
            @Field("motDePasseUser") String motDePasseUser
    );

    //AJOUT MOTIF ---------------------------------------------
    @POST("ajouter.php")
    @FormUrlEncoded
    Call<ResponseBody> ajoutMotif(
            @Field("idMotif") int idMotif,
            @Field("idType") int idType,
            @Field("idUser") int idUser,
            @Field("dateCreation") String dateCreation,
            @Field("source") String source,
            @Field("nomMotif") String nomMotif,
            @Field("imgCreation") String imgCreation,
            @Field("dataJson") String dataJson
    );

    //AJOUT USER ---------------------------------------------
    @POST("ajouterUser.php")
    @FormUrlEncoded
    Call<ResponseBody> ajoutUser(
            @Field("idUser") int idUser,
            @Field("nomUser") String nomUser,
            @Field("motDePasseUser") String motDePasseUser
    );

    //MODIFIER MOTIF ---------------------------------------------
    @POST("modifier.php")
    @FormUrlEncoded
    Call<ResponseBody> modifierMotif(
            @Field("idMotif") int idMotif,
            @Field("idType") int idType,
            @Field("idUser") int idUser,
            @Field("dateCreation") String dateCreation,
            @Field("source") String source,
            @Field("nomMotif") String nomMotif,
            @Field("imgCreation") String imgCreation,
            @Field("dataJson") String dataJson
    );


    //SUPPRIMER MOTIF ---------------------------------------------
    @POST("supprimer.php")
    @FormUrlEncoded
    Call<ResponseBody> supprimerMotif(
      @Field("idMotif") int idMotif,
      @Field("idUser") int idUser,
      @Field("idType") int idType,
      @Field("source") String source,
      @Field("dateCreation") String dateCreation,
      @Field("nomMotif") String nomMotif,
      @Field("imgCreation") String imgCreation,
      @Field("dataJson") String dataJson
    );
}
