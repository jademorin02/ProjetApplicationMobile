package com.example.projetapplicationmobilemarkus;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_register extends Fragment {

    //DÉCLARATIONS
    EditText ETNomUtilisateurRegister, ETPWDUtilisateurRegister, ETPWDUtilisateurRegisterAgain;
    TextView TVErreurRegister;
    Button btnRegister;

    Activity contextRegister;

    public fragment_register() {
        //DOIT RESTER VIDE ---------------------------------
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contextRegister = getActivity();
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    public void onStart() {
        super.onStart();

        ETNomUtilisateurRegister = contextRegister.findViewById(R.id.ETNomRegister);
        ETPWDUtilisateurRegister = contextRegister.findViewById(R.id.ETPwdRegister);
        ETPWDUtilisateurRegisterAgain = contextRegister.findViewById(R.id.ETPwdRegisterAGAIN);

        TVErreurRegister = contextRegister.findViewById(R.id.TVErreurRegister);

        btnRegister = contextRegister.findViewById(R.id.btnRegister);

        TVErreurRegister.setText("");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //VÉRIFICATIONS DES EDITTEXT
                //USER VIDE ET/OU MOT DE PASSE
                if(ETNomUtilisateurRegister.getText().toString().equals("") ||
                        ETPWDUtilisateurRegister.getText().toString().equals("") ||
                        ETPWDUtilisateurRegisterAgain.getText().toString().equals(""))
                {
                    TVErreurRegister.setText("*Veuillez remplir tous les champs");
                }

                else if (!ETPWDUtilisateurRegister.getText().toString().matches("^(?!.*[ âŝêŷûîôĵĝŝĉèàéç]).*$") &&
                        !ETPWDUtilisateurRegisterAgain.getText().toString().matches("^(?!.*[ âŝêŷûîôĵĝŝĉèàéç]).*$"))
                {
                    TVErreurRegister.setText("*Veuillez ne pas insérer d'accent dans le mot de passe");
                }

                //MATCH MOT DE PASSE ?
                else if(!ETPWDUtilisateurRegister.getText().toString().matches
                        (ETPWDUtilisateurRegisterAgain.getText().toString())
                || !ETPWDUtilisateurRegisterAgain.getText().toString().matches
                        (ETPWDUtilisateurRegister.getText().toString()))
                {
                    TVErreurRegister.setText("*Veuillez entrer des mots de passe identiques");
                }

                if (
                        (!ETNomUtilisateurRegister.getText().toString().equals("")) &&
                        (ETNomUtilisateurRegister.getText().toString().length() < 255) &&
                        (!ETPWDUtilisateurRegister.getText().toString().equals("")) &&
                        (ETPWDUtilisateurRegisterAgain.getText().toString().matches("^(?!.*[ âŝêŷûîôĵĝŝĉèàéç]).*$")) &&
                        (ETPWDUtilisateurRegister.getText().toString().matches("^(?!.*[ âŝêŷûîôĵĝŝĉèàéç]).*$")) &&
                        (ETPWDUtilisateurRegister.getText().toString().matches(ETPWDUtilisateurRegisterAgain.getText().toString()))    &&
                        (!ETPWDUtilisateurRegister.getText().toString().equals("")) &&
                        (ETPWDUtilisateurRegisterAgain.getText().toString().matches(ETPWDUtilisateurRegister.getText().toString())))
                {
                    //AJOUTER L'UTILISATEUR
                    Utilisateur u = new Utilisateur(0, ETNomUtilisateurRegister.getText().toString(),
                            ETPWDUtilisateurRegister.getText().toString());

                    //AJOUTER DANS LA BD
                    InterfaceServeur serveur = RetrofitInstance.getInstance().create((InterfaceServeur.class));
                    Call<ResponseBody> call = serveur.ajoutUser(u.getIdUser(), u.getNomUser(), u.getMotDePasseUser());

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String message = null;
                            //SI FONCTIONNE = ON AJOUTE*
                            try {
                                message = response.body().string();
                                System.out.println(message);
                                u.setIdUser(Integer.parseInt(message));

                                btnRegister.setEnabled(false);
                                TVErreurRegister.setText("*Dirigez-vous dans la page Login afin " +
                                        "de vous connecter!");


                                //SI FONCTIONNE PAS ON RÉESSAIE*
                            } catch (Exception e) {
                                e.printStackTrace();
                                btnRegister.setEnabled(true);
                            }
                        }

                        //ÉCHEC
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            TVErreurRegister.setText("*Erreur de connexion avec la Base de données, veuillez-réessayer");
                            btnRegister.setEnabled(true);
                        }
                    });
                }
            }
        });
    }
}