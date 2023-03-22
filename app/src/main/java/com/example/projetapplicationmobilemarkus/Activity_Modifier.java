package com.example.projetapplicationmobilemarkus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Modifier extends AppCompatActivity {

    //DÉCLARATIONS
    EditText ETNomMotifModifier, ETImageModifier, ETDateMotifModifier, ETCreateurMotifModifier;
    TextView TVNomMotifErreurModifier, TVTypeMotifErreurModifier, TVImageMotifErreurModifier,
             TVCreateurMotifErreurModifier, TVDateMotifErreurModifier ;
    Button btnModifierMotif;
    ImageButton imgBtnFichierModifier, imgBtnCalendarPicker;
    ImageView IVPreviewImage;
    RadioGroup radioGroupTypeModifier;
    RadioButton BtnRadioPersonnaliseModifier, BtnRadioBaseModifier;
    // -------------------------------------------------------------------------
    //INT
    int SELECT_PICTURE = 200, idType, idMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        //FINDVIEWBYID
        ETNomMotifModifier = findViewById(R.id.ETNomMotifModifier);
        ETCreateurMotifModifier = findViewById(R.id.ETCreateurMotifModifier);
        ETDateMotifModifier = findViewById(R.id.ETDateMotifModifier);
        ETImageModifier = findViewById(R.id.ETUrlImageModifier);

        //TEXT VIEW
        TVNomMotifErreurModifier = findViewById(R.id.TVNomMotifErreurModifier);
        TVCreateurMotifErreurModifier = findViewById(R.id.TVCreateurMotifErreurModifier);
        TVTypeMotifErreurModifier = findViewById(R.id.TVTypeMotifErreurModifier);
        TVImageMotifErreurModifier = findViewById(R.id.TVImageMotifErreurModifier);
        TVDateMotifErreurModifier = findViewById(R.id.TVDateMotifErreurModifier);

        //BUTTON
        btnModifierMotif = findViewById(R.id.btnModifierMotif);

        //IMAGE BUTTON
        imgBtnFichierModifier = findViewById(R.id.imgButtonFileModifier);
        imgBtnCalendarPicker = findViewById(R.id.imgButtonDatePicker);

        //IMAGE VIEW
        IVPreviewImage = findViewById(R.id.IVPreviewImageModifier);

        //RADIO GROUP
        radioGroupTypeModifier = findViewById(R.id.radioGroupeTypeModifier);

        //RADIO BUTTON
        BtnRadioBaseModifier = findViewById(R.id.BtnRadioBaseModifier);
        BtnRadioPersonnaliseModifier = findViewById(R.id.BtnRadioPersonnelModifier);

        //INTENT (PUTEXTRA)
        //Entrer les valeurs dans les champs de cette position
        Intent intent = getIntent();

        idMotif = intent.getIntExtra("idMotif", -1);

        ETNomMotifModifier.setText(intent.getStringExtra("nomMotif"));
        ETCreateurMotifModifier.setText(intent.getStringExtra("source"));
        ETImageModifier.setText(intent.getStringExtra("imgCreation"));
        ETDateMotifModifier.setText(intent.getStringExtra("dateCreation"));
        Picasso.get().load(intent.getStringExtra("imgCreation"));

        //Cocher les boutons par défauts (celui qui est déjà choisit)
        if(intent.getIntExtra("idType", -1) == 1)
        {
            BtnRadioBaseModifier.setChecked(true);
            idType = 1;
        }
        else if(intent.getIntExtra("idType", -1) == 2)
        {
            BtnRadioPersonnaliseModifier.setChecked(true);
            idType = 2;
        }

        Picasso.get().load(intent.getStringExtra("imgCreation")).into(IVPreviewImage);


        //CHOISIR UNE IMAGE
        imgBtnFichierModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                imageChooser();
            }
        });


        //CHOISIR UN DATE À L'AIDE D'UN CALENDARPICKER
        imgBtnCalendarPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //INSTANCE DU CALENDRIER
                final Calendar c = Calendar.getInstance();

                //DÉFINIR JOUR, MOIS, ANNÉE
                int annee = c.get(Calendar.YEAR);
                int mois = c.get(Calendar.MONTH);
                int jour = c.get(Calendar.DAY_OF_MONTH);

                //CRÉER UNE VARIABLE POUR LE CALENDAR PICKER
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        //PASSER LE CONTEXT
                        Activity_Modifier.this, new DatePickerDialog.OnDateSetListener()
                {
                            @Override
                            public void onDateSet(DatePicker view, int annee,
                                                  int mois, int jour) {
                                //SET LA DATE DANS NOTRE EDIT_TEXT
                                ETDateMotifModifier.setText(annee + "-" + (mois + 1) + "-" + jour);
                            }
                        },
                        //ON PASSE LA DATE
                        annee, mois, jour);
                //APPEL DU CALENDAR
                datePickerDialog.show();
            }
        });
    }

    //--------------------------------------------------------------------------------------
    // ONRADIOBUTTONCLICKED() ---------------------------------------------
    public void onRadioButtonClicked(View view) {
        // Est-ce qu'un bouton est cocher
        boolean checked = ((RadioButton) view).isChecked();

        // Vérifier quel bouton radio à été cocher
        switch(view.getId()) {
            case R.id.BtnRadioBase:
                if (checked && BtnRadioBaseModifier.getText().toString().equals("Motif de base"))
                {
                    BtnRadioBaseModifier.isChecked();
                    //Valeur du bouton radio "MOTIF DE BASE"
                    idType = 1;
                }
                break;

            case R.id.BtnRadioPersonnel:
                if (checked && BtnRadioPersonnaliseModifier.getText().toString().equals("Personnalisé"))
                {
                    BtnRadioPersonnaliseModifier.isChecked();
                    //Valeur du bouton radio "MOTIF PERSONNALISÉ"
                    idType = 2;
                }
                break;
        }
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
          //Symbole dans le nom
          else if(!ETNomMotifModifier.getText().toString().matches("^(?!.*[!@#$%?&*()=+^;,~}{¤¨<>:]).*$"))
          {
              TVNomMotifErreurModifier.setText("*Veuillez ne pas insérer de symbole autres que '_', '-' et " +
                      "'.'");
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
          else if(!ETDateMotifModifier.getText().toString().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))
          {
              TVDateMotifErreurModifier.setText("*Veuillez entrer un format de date valide  (aaaa-mm-jj)");
          }
          //Longueur = 10
          else if(ETDateMotifModifier.getText().toString().length() != 10)
          {
              TVDateMotifErreurModifier.setText("*Veuillez entrer un format de date valide (aaaa-mm-jj)");
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
          //Symbole dans le créateur
          else if(!ETCreateurMotifModifier.getText().toString().matches("^(?!.*[!@#$%?&*()=+^;,~}{¤¨<>:]).*$"))
          {
              TVCreateurMotifErreurModifier.setText("*Veuillez ne pas insérer de symbole autres que '_', '-' et " +
                      "'.'");
          }
          //OK
          else
          {
              TVCreateurMotifErreurModifier.setText("");
          }

          //Modifier le motif s'il passe par tous les tests
          if((!ETNomMotifModifier.getText().toString().equals("")) &&
             (ETNomMotifModifier.getText().toString().length() < 255) &&
             (ETNomMotifModifier.getText().toString().matches("^(?!.*[!@#$%?&*()=+^;,~}{¤¨<>:]).*$")) &&
             (!ETDateMotifModifier.getText().toString().equals("")) &&
             (ETDateMotifModifier.getText().toString().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) &&
             (ETDateMotifModifier.getText().toString().length() == 10) &&
             (!ETImageModifier.getText().toString().equals("")) &&
             (!ETCreateurMotifModifier.getText().toString().equals("")) &&
             (ETCreateurMotifModifier.getText().toString().matches("^(?!.*[!@#$%?&*()=+^;,~}{¤¨<>:]).*$")) &&
             (ETCreateurMotifModifier.getText().toString().length() < 255))
          {

              btnModifierMotif.setEnabled(false);

              //Modifier le motif
              Motif m = new Motif(idMotif, idType , fragment_login.idUser,
                      ETDateMotifModifier.getText().toString(),
                      ETCreateurMotifModifier.getText().toString(),
                      ETNomMotifModifier.getText().toString(),
                      ETImageModifier.getText().toString(), ETNomMotifModifier.getText().toString());

              //Modifier le motif
              InterfaceServeur serveur = RetrofitInstance.getInstance().create((InterfaceServeur.class));
              Call<ResponseBody> call = serveur.modifierMotif(m.getIdMotif(), m.getIdType(), m.getIdUser(),
                      m.getDateCreation(), m.getSource(), m.getNomMotif(), m.getImgCreation(),
                      m.getdataJson());

              call.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      String message = null;


                      //SI FONCTIONNE = ON MODIFIE*
                      try {
                          message = response.body().string();
                          System.out.println(message);

                          if(message.equals("1"))
                          {
                             // m.setIdMotif(Integer.parseInt(message));
                              MainActivity.adapterMotif.modifierMotif(m.idMotif, m);
                              finish();
                          }

                          else
                          {
                              System.out.println(idType);
                              System.out.println("ERREUR de modification");
                          }

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
    //------------------ ONBACKPRESSED() ------------------
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

        //Bouton Non pour rester sur la page -------------------------------------------------------
        btNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog1.dismiss();
            }
        });

        //Bouton Oui pour quitter et retourner à l'accueil -----------------------------------------
        btOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        alertDialog1.show();
    }


    //--------------------------------------------------------------------------------------
    // IMAGECHOOSER() ---------------------------------------------
    void imageChooser()
    {
        //Créer une instance de type INTENT de type IMAGE
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        //Passer la constante à comparer
        startActivityForResult(Intent.createChooser(i, "Choisir une image dans vos fichiers"), SELECT_PICTURE);
    }

    //--------------------------------------------------------------------------------------
    // ONACTIVITYRESULT() ---------------------------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode ==  RESULT_OK)
        {
            //Comparer le resultat avec l'image PICK
            if(requestCode == SELECT_PICTURE)
            {
                //Prendre le URL de l'image
                Uri selectedImageUri = data.getData();

                if(null != selectedImageUri)
                {
                    IVPreviewImage.setImageURI(selectedImageUri);
                    ETImageModifier.setText(selectedImageUri.toString());
                }
            }
        }
    }
}