package com.example.projetapplicationmobilemarkus;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_AjoutMotif extends AppCompatActivity {

    //DÉCLARATIONS
    //EDIT TEXT
    EditText ETNomMotifAjout, ETImageAjout, ETDateMotifAjout, ETCreateurMotifAjout;

    //IMAGE BUTTON
    ImageButton imgBtnFichierImg;

    //IMAGE VIEW
    ImageView IVPreviewImage;

    //RADIO GROUP
    RadioGroup radioGroupType;

    //RADIO BUTTON
    RadioButton BtnRadioPersonnalise, BtnRadioBase;
    int idType;
    int SELECT_PICTURE = 200;

    //TEXT VIEW
    TextView TVNomMotifErreur, TVTypeMotifErreur, TVImageMotifErreur, TVCreateurMotifErreur ;

    //BUTTON
    Button btnAjouterMotif;

    //CALENDAR (DATE)
    Calendar calendar;
    String dateCourrante = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA_FRENCH.getDefault()).format(new Date());

    //--------------------------------------------------------------------------------------
    // ONCREATE() ---------------------------------------------
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_motif);

        //FINDVIEWBYID ---------------------------------------------
        //BUTTON - IMAGEBUTTON
        btnAjouterMotif = findViewById(R.id.btnAjouterMotif);
        imgBtnFichierImg = findViewById(R.id.imageButtonFileImg);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        //RADIOBUTTON - RADIOGROUP
        BtnRadioBase = findViewById(R.id.BtnRadioBase);
        BtnRadioPersonnalise = findViewById(R.id.BtnRadioPersonnel);
        radioGroupType = findViewById(R.id.radioGroupeType);

        //EDITVIEW
        ETNomMotifAjout = findViewById(R.id.ETNomMotifAjout);
        ETCreateurMotifAjout = findViewById(R.id.ETCreateurMotifAjout);
        ETImageAjout = findViewById(R.id.ETImageAjouter);

        //TEXTVIEW
        TVNomMotifErreur = findViewById(R.id.TVNomMotifErreur);
        TVTypeMotifErreur = findViewById(R.id.TVTypeMotifErreur);
        TVImageMotifErreur = findViewById(R.id.TVImageMotifErreur);
        TVCreateurMotifErreur = findViewById(R.id.TVCreateurMotifErreur);

        //Entrer les valeurs dans les champs de cette position
        Intent intent = getIntent();

        ETNomMotifAjout.setText(intent.getStringExtra("NomMotif"));
        ETCreateurMotifAjout.setText(intent.getStringExtra("CreateurMotif"));
       // ETDateMotifAjout.setText(dateCourrante);
        ETImageAjout.setText(intent.getStringExtra("ImageMotif"));

        calendar = Calendar. getInstance();

        //Set les boutons radio à nul par précaution
        BtnRadioBase.setChecked(false);
        BtnRadioPersonnalise.setChecked(false);

        idType = 0;


        //CHOISIR UNE IMAGE
        imgBtnFichierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }



    //--------------------------------------------------------------------------------------
    // ONCREATEOPTIONSMENU() ---------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);

        //Icône qui devienne enable dans la page active
        // BOUTON CATALOGUE (0)
        menu.getItem(0).setEnabled(true);
        menu.getItem(0).getIcon().setAlpha(255);

        //BOUTON AJOUTER (1)
        menu.getItem(1).setEnabled(false);
        menu.getItem(1).getIcon().setAlpha(125);

        //BOUTON PARAMÈTRES (2)
        menu.getItem(2).setEnabled(true);
        menu.getItem(2).getIcon().setAlpha(255);
        return true;
    }


    //--------------------------------------------------------------------------------------
    // ONOPTIONSITEMSELECTED() ---------------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            //BOUTON ITEM ROBOT (PARAMÈTRES)
            case R.id.ItmRobot:
            {
                //Appeler une activité
                  Intent intent = new Intent(this, Activity_Parametres.class);
                  startActivity(intent);
                  finish();
                return true;
            }

            //BOUTON CATALOGUE (VISUALISER CATALOGUE)
            case R.id.ItmCatalogue:
            {
                //Appeler une activité
                Intent intent = new Intent(this, Activity_Catalogue.class);
                startActivity(intent);
                finish();
                return true;
            }

            //BOUTON AJOUTER (AJOUTER UN MOTIF)
            case R.id.ItmAjouterMotif:
            {
                //Appeler une activité
                Intent intent = new Intent(this, Activity_AjoutMotif.class);
                startActivity(intent);
                finish();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //--------------------------------------------------------------------------------------
    // ONBACKPRESSED() ---------------------------------------------
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

         //Bouton NON pour **RESTER** sur la page
          btNon.setOnClickListener(new View.OnClickListener()
          {
             @Override
              public void onClick(View v)
             {
                   alertDialog1.dismiss();
             }
          });

          //Bouton OUI pour **QUITTER** la page Ajout
          btOui.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v)
              {
                  Intent intent = new Intent(v.getContext(), Activity_Catalogue.class);
                  startActivity(intent);
                  finish();
              }
          });

          alertDialog1.show();
    }


    //--------------------------------------------------------------------------------------
    // ONRADIOBUTTONCLICKED() ---------------------------------------------
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.BtnRadioBase:
                if (checked && BtnRadioBase.getText().toString().equals("Motif de base"))
                {
                    BtnRadioBase.isChecked();
                    //Insérer la valeur

                    idType = 1;
                }
                break;
            case R.id.BtnRadioPersonnel:
                if (checked && BtnRadioPersonnalise.getText().toString().equals("Personnalisé"))
                {
                    BtnRadioPersonnalise.isChecked();
                    //Insérer la valeur
                    idType = 2;
                }
                break;
        }
    }


    //--------------------------------------------------------------------------------------
    // GESTIONCLICKAJOUTER() ---------------------------------------------
    public void gestionClickAjouter(View view)
    {
     //Si on clique sur le BOUTON AJOUTER
     if(view.getId() == R.id.btnAjouterMotif)
     {
         //--------------------------------- VÉRIFICATIONS ---------------------------------
         //CHAMPS NOM -------------------------------------
         //Vide
         if (ETNomMotifAjout.getText().toString().equals(""))
         {
             TVNomMotifErreur.setText("*Veuillez entrer un nom");
         }
         //Trop long
        else if (ETNomMotifAjout.getText().toString().length() > 255)
        {
             TVNomMotifErreur.setText("*Veuillez entrer un nom de 255 caractères et moins");
        }
        //OK
        else
         {
             TVNomMotifErreur.setText("");
         }


         //CHAMPS TYPE -------------------------------------
         //Vide
         if(!BtnRadioBase.isChecked() && !BtnRadioPersonnalise.isChecked())
         {
             TVTypeMotifErreur.setText("*Veuillez sélectionné un type");
         }
         else
         {
             TVTypeMotifErreur.setText("");
         }

         //CHAMPS DATE -------------------------------------
         //Sera insérer avec la date du jour automatiquement
         //CEPENDANT, on peut la modifier seulement et la visualiser

         //CHAMPS IMAGE -------------------------------------
         //Vide
         if (ETImageAjout.getText().toString().equals("")) {
             TVImageMotifErreur.setText("");
         }


         //CHAMPS CRÉATEUR -------------------------------------
        //Vide
         if(ETCreateurMotifAjout.getText().toString().equals(""))
         {
             TVCreateurMotifErreur.setText("*Veuillez entrer un créateur");
         }
         //Champs trop long
         else if(ETCreateurMotifAjout.getText().toString().length() > 255)
         {
             TVCreateurMotifErreur.setText("*Veuillez entrer un créateur de 255 caractères et moins");
         }
         //OK
         else
         {
             TVCreateurMotifErreur.setText("");
         }


         //Ajouter le motif s'il passe par tous les tests
         if((!ETNomMotifAjout.getText().toString().equals("")                       &&
            (ETNomMotifAjout.getText().toString().length() < 255)                   &&
            (!ETCreateurMotifAjout.getText().toString().equals(""))                 &&
            (ETCreateurMotifAjout.getText().toString().length() < 255)))
         {

             btnAjouterMotif.setEnabled(false);

             //Ajouter le motif
             Motif m = new Motif(0, idType, fragment_login.idUser, dateCourrante,
                     ETCreateurMotifAjout.getText().toString(),
                     ETNomMotifAjout.getText().toString(),
                     ETImageAjout.getText().toString());

             //Ajouter le motif
             InterfaceServeur serveur = RetrofitInstance.getInstance().create((InterfaceServeur.class));
             Call<ResponseBody> call = serveur.ajoutMotif(m.getIdMotif(), m.getIdType(), m.getIdUser(),
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
                         MainActivity.adapterMotif.ajouterMotif(m);
                         finish();

                         //SI FONCTIONNE PAS ON RÉESSAIE*
                     } catch (Exception e) {
                         e.printStackTrace();
                         btnAjouterMotif.setEnabled(true);
                     }

                 }

                 //ÉCHEC
                 @Override
                 public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.err.println(t);
                    btnAjouterMotif.setEnabled(true);
                 }
             });
         }
      }
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
        startActivityForResult(Intent.createChooser(i, "Choisir une image"), SELECT_PICTURE);
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
                    ETImageAjout.setText(selectedImageUri.toString());
                }
            }
        }


    }


}