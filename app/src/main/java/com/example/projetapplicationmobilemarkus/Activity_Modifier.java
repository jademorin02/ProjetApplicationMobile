package com.example.projetapplicationmobilemarkus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class Activity_Modifier extends AppCompatActivity {

    //DÉCLARATIONS
    EditText ETNomMotifModifier, ETImageModifier, ETDateMotifModifier, ETCreateurMotifModifier,
            ETTypeModifier, ETUserModifier;

    ImageButton imgBtnFichierModifier;
    RadioGroup radioGroupTypeModifier;
    RadioButton BtnRadioPersonnaliseModifier, BtnRadioBaseModifier;
    TextView TVNomMotifErreurModifier, TVTypeMotifErreurModifier, TVImageMotifErreurModifier,
            TVCreateurMotifErreurModifier, TVDateMotifErreurModifier ;
    Button btnModifierMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        //FINDVIEWBYID
        btnModifierMotif = findViewById(R.id.btnModifierMotif);
        ETNomMotifModifier = findViewById(R.id.ETNomMotifModifier);
        ETCreateurMotifModifier = findViewById(R.id.ETCreateurMotifModifier);
        ETDateMotifModifier = findViewById(R.id.ETDateMotifModifier);
        ETImageModifier = findViewById(R.id.ETImageModifier);

        TVNomMotifErreurModifier = findViewById(R.id.TVNomMotifErreurModifier);
        TVCreateurMotifErreurModifier = findViewById(R.id.TVCreateurMotifErreurModifier);
        TVTypeMotifErreurModifier = findViewById(R.id.TVTypeMotifErreurModifier);
        TVImageMotifErreurModifier = findViewById(R.id.TVImageMotifErreurModifier);
        TVDateMotifErreurModifier = findViewById(R.id.TVDateMotifErreurModifier);

        BtnRadioBaseModifier = findViewById(R.id.BtnRadioBaseModifier);
        BtnRadioPersonnaliseModifier = findViewById(R.id.BtnRadioPersonnelModifier);
        radioGroupTypeModifier = findViewById(R.id.radioGroupeTypeModifier);


        //Entrer les valeurs dans les champs de cette position
        Intent intent = getIntent();
        ETNomMotifModifier.setText(intent.getStringExtra("nomMotif"));
        ETCreateurMotifModifier.setText(intent.getStringExtra("source"));
        ETImageModifier.setText(intent.getStringExtra("imgCreation"));
        ETDateMotifModifier.setText(intent.getStringExtra("dateCreation"));
        Picasso.get().load(intent.getStringExtra("imgCreation"));

        //Cocher les boutons par défauts (celui qui est déjà choisit)
//        if(intent.getBooleanExtra("idType", ) == 1)
//        {
//            BtnRadioPersonnaliseModifier.setChecked(true);
//        }
//        else
//        {
//            BtnRadioBaseModifier.setChecked(true);
//        }
        //Picasso.get().load(m.imgCreation).into(ImgMotif);
    }



    //--------------------------------------------------------------------------------------
    //------------------ GestionClikMotif() ------------------
    public void gestionClickMotif(View view)
    {
        //Si on clique sur le bouton Ajouter
        if(view.getId() == R.id.btnModifierMotif)
        {
        //--------------------------------- VÉRIFICATIONS ---------------------------------
        //CHAMPS NOM -------------------------------------
          //Vide
          if (ETNomMotifModifier.getText().toString().equals(""))
          {
              TVNomMotifErreurModifier.setText("*Veuillez entrer un nom");
          }
          //Trop long
          else if(ETNomMotifModifier.getText().toString().length() > 255)
          {
              TVNomMotifErreurModifier.setText("*Veuillez entrer un nom de 255 caractères et moins");
          }
          //OK
          else
          {
            TVNomMotifErreurModifier.setText("");
          }

        //CHAMPS DATE -------------------------------------
          //Vide
          if(ETDateMotifModifier.getText().toString().equals(""))
          {
              TVDateMotifErreurModifier.setText("*Veuillez entrer une date");
          }
          //Format pas valide
          else if(!ETDateMotifModifier.getText().toString().matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$"))
          {
              TVDateMotifErreurModifier.setText("*Veuillez entrer un format de date valide");
          }
          //Longueur = 10
          else if(ETDateMotifModifier.getText().toString().length() != 10)
          {
              TVDateMotifErreurModifier.setText("*Veuillez entrer un format de date valide (dd/mm/yyyy");
          }
          //OK
          else
          {
              TVDateMotifErreurModifier.setText("");
          }

        //CHAMPS IMAGE -------------------------------------
          //Vide
          if(ETImageModifier.getText().toString().equals(""))
          {
              TVImageMotifErreurModifier.setText("*Veuillez entrer une image");
          }
          //OK
          else
          {
              TVImageMotifErreurModifier.setText("");
          }

        //CHAMPS CRÉATEUR -------------------------------------
          //Vide
          if(ETCreateurMotifModifier.getText().toString().equals(""))
          {
              TVCreateurMotifErreurModifier.setText("*Veuillez entrer un créateur");
          }
          //Champs trop long
          else if(ETCreateurMotifModifier.getText().toString().length() > 255)
          {
              TVCreateurMotifErreurModifier.setText("*Veuillez entrer un créateur de 255 caractères et moins");
          }
          //OK
          else
          {
              TVCreateurMotifErreurModifier.setText("");
          }

          //Modifier le motif s'il passe par tous les tests
          if((!ETNomMotifModifier.getText().toString().equals("")) &&
             (ETNomMotifModifier.getText().toString().length() < 255) &&
             (!ETDateMotifModifier.getText().toString().equals("")) &&
             (ETDateMotifModifier.getText().toString().matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}")) &&
             (ETDateMotifModifier.getText().toString().length() == 10) &&
             (!ETImageModifier.getText().toString().equals("")) &&
             (!ETCreateurMotifModifier.getText().toString().equals("")) &&
             (ETCreateurMotifModifier.getText().toString().length() < 255))
          {

              btnModifierMotif.setEnabled(false);

              //Modifier le motif
              Motif m = new Motif(0, 1, 0, ETDateMotifModifier.getText().toString(),
                      ETCreateurMotifModifier.getText().toString(),
                      ETNomMotifModifier.getText().toString(),
                      ETImageModifier.getText().toString());

              //Ajouter le motif
              InterfaceServeur serveur = RetrofitInstance.getInstance().create((InterfaceServeur.class));
              Call<ResponseBody> call = serveur.modifierMotif(m.getIdMotif(), m.getIdType(), m.getIdUser(),
                      m.getDateCreation(), m.getSource(), m.getNomMotif(), m.getImgCreation());

              call.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      String message = null;


                      //SI FONCTIONNE = ON AJOUTE*
                      try {
                          message = response.body().string();
                          System.out.println(message);
                          m.setIdMotif(Integer.parseInt(message));
                          MainActivity.adapterMotif.modifierMotif(m.idMotif, m);
                          finish();

                          //SI FONCTIONNE PAS ON RÉESSAIE*
                      } catch (Exception e) {
                          e.printStackTrace();
                          btnModifierMotif.setEnabled(true);
                      }

                  }

                  //ÉCHEC
                  @Override
                  public void onFailure(Call<ResponseBody> call, Throwable t) {
                      System.err.println(t);
                      btnModifierMotif.setEnabled(true);
                  }
              });
          }
        }
            }

    //--------------------------------------------------------------------------------------
    //------------------ onBackPressed() ------------------
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        View view = View.inflate(this, R.layout.menu_dialog_quitter_ajout, null);
        builder.setView(view);

        AlertDialog alertDialog1 = builder.create();

        //Attributs selon le contexte -
        Button btOui = view.findViewById(R.id.btnOuiDialog);
        Button btNon = view.findViewById(R.id.btnNonDialog);
        TextView tvTextBoite = view.findViewById(R.id.tvConfirmation);
        tvTextBoite.setText("Voulez-vous abandonner vos modifications et retournez à l'accueil?");

        //Bouton Non pour rester sur la page
        btNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog1.dismiss();
            }
        });

        //Bouton Oui pour quitter et retourner à l'accueil
        btOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        alertDialog1.show();
    }


}