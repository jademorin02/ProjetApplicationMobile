package com.example.projetapplicationmobilemarkus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Connexion_Activity extends AppCompatActivity {

    /*
           INFORMATIONS DE CONNEXION

            USER : admin
            PASSWORD : admin

     */

    //DÉCLARATIONS
    EditText ETNomUser, ETPwd;
    TextView TVMessageErreur;
    Button BTNConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        TVMessageErreur = findViewById(R.id.TVMessageErreurConnexion);

        ETNomUser = findViewById(R.id.ETNomUtilisateurConnexion);
        ETPwd = findViewById(R.id.ETPwdConnexion);

        BTNConnexion = findViewById(R.id.BTNConnexion);
    }

    //--------------------------------------------------------------------------------------
    //------------------ GestionClikConnexion() ------------------
    public void GestionClickConnexion(View view)
    {
        if(view.getId() == R.id.BTNConnexion)
        {
            //VÉRIFICATIONS DES EDITTEXT
            //USER VIDE ET/OU MOT DE PASSE
            if(ETPwd.getText().toString().equals("") ||
                    ETNomUser.getText().toString().equals(""))
            {
                TVMessageErreur.setText("*Veuillez remplir tous les champs");
            }

            //MATCH ?
            else if(!ETNomUser.getText().toString().equals("admin") ||
                    !ETPwd.getText().toString().equals("admin"))
            {
                TVMessageErreur.setText("*Nom d'utilisateur ou mot de passe invalide!");
            }

            //OK
            else if (ETNomUser.getText().toString().equals("admin") &&
                    ETPwd.getText().toString().equals("admin"))
            {
                TVMessageErreur.setText("");

                //Appeler une activité à démarrer = MAIN ACTIVITY
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}