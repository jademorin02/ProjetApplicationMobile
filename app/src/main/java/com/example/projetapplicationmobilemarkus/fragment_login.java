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


                if(ETNomUtilisateurConnexion_.getText().toString().equals("") ||
                        ETPWDUtilisateurConnexion_.getText().toString().equals(""))
                {
                    TVErreurConnexion.setText("*Veuillez remplir tous les champs");
                }

                //MATCH ?
                else if(!ETNomUtilisateurConnexion_.getText().toString().equals("admin") ||
                        !ETPWDUtilisateurConnexion_.getText().toString().equals("admin"))
                {
                    TVErreurConnexion.setText("*Nom d'utilisateur ou mot de passe invalide!");
                }

                //OK
                else if (ETNomUtilisateurConnexion_.getText().toString().equals("admin") &&
                        ETPWDUtilisateurConnexion_.getText().toString().equals("admin"))
                {
                    TVErreurConnexion.setText("");

                    //Appeler une activité à démarrer = MAIN ACTIVITY
                    TVErreurConnexion.setText("");

                    Intent intent = new Intent(context, Activity_Catalogue.class);
                    startActivity(intent);
                    context.finish();

                }

            }
        });


   }
}

