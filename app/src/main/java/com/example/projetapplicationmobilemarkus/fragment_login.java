package com.example.projetapplicationmobilemarkus;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_login extends Fragment {

    EditText ETNomUtilisateurConnexion_, ETPWDUtilisateurConnexion_;
    TextView TVErreurConnexion;
    Button btnConnexion;

    Activity context;

    public fragment_login() {
        //DOIT RESTER VIDE ---------------------------------
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        return inflater.inflate(R.layout.fragment_login, container, false);
   }

   public void onStart()
   {
       super.onStart();

       ETNomUtilisateurConnexion_ = context.findViewById(R.id.ETNomUtilisateurConnexion_);
       ETPWDUtilisateurConnexion_ = context.findViewById(R.id.ETPwdConnexion_);
       TVErreurConnexion = context.findViewById(R.id.TVErreurConnexion);

       btnConnexion = context.findViewById(R.id.btnConnexion_);

       TVErreurConnexion.setText("");

       btnConnexion.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //VÉRIFICATIONS DES EDITTEXT
               //USER VIDE ET/OU MOT DE PASSE

               //Fonctionne avec admin pour le moment


                   TVErreurConnexion.setText("");

                   //Ajouter le User
                   Utilisateur u = new Utilisateur(0, ETNomUtilisateurConnexion_.getText().toString(),
                           ETPWDUtilisateurConnexion_.getText().toString());

                   //CONNEXION UTILISATEUR
                   InterfaceServeur serveur = RetrofitInstance.getInstance().create(InterfaceServeur.class);
                   Call<ResponseBody> call = serveur.getUser(u.getIdUser(), u.getNomUser(), u.getMotDePasseUser());

                   call.enqueue(new Callback<ResponseBody>()
                   {
                       @Override
                       public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                       {
                           String message = null;

                           //SI FONCTIONNE = ON CONNECTE*
                           try {
                               message = response.body().string();
                               System.out.println(message + "------------------------------------------------------");
                              // u.setIdUser(Integer.parseInt(message));
                              // context.finish();

                               //SI FONCTIONNE PAS ON RÉESSAIE*
                           } catch (Exception e) {
                               e.printStackTrace();
                               btnConnexion.setEnabled(true);
                           }

                       }

                       //ÉCHEC
                       @Override
                       public void onFailure(Call<ResponseBody> call, Throwable t) {
                           System.err.println(t);
                           btnConnexion.setEnabled(true);
                       }


//                    //Appeler une activité à démarrer = MAIN ACTIVITY
//                    TVErreurConnexion.setText("");
//
//                    Intent intent = new Intent(context, Activity_Catalogue.class);
//                    startActivity(intent);
//                    context.finish();

                   });
               }

       });
   }
}


