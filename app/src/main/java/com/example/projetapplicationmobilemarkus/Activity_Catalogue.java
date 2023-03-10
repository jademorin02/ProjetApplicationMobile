package com.example.projetapplicationmobilemarkus;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Catalogue extends AppCompatActivity implements interfaceGestionClic
{

    //DÉCLARATIONS

    //RECYCLER VIEW
    RecyclerView recyclerView;


    //INTERFACE GESTION CLIC
    interfaceGestionClic gestionClic;


    //ACTIVITY RESULT LAUNCHER
    ActivityResultLauncher<Intent> resultLauncher;

    //BUTTON
    Button btnAjouterCatalogue;


    //--------------------------------------------------------------------------------------
    // ------------------------ ONCREATE() ------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        btnAjouterCatalogue = findViewById(R.id.BtnAjouterCatalogue);

        resultLauncher = registerForActivityResult(

                //---------------------------------------------------------------------------
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }
        );


        recyclerView = findViewById(R.id.RVMotifs);
        recyclerView.setLayoutManager((new GridLayoutManager(this,2)));

        MainActivity.adapterMotif = new AdapterMotif(new ArrayList<Motif>());
        MainActivity.adapterMotif.gestionClic = this;
        recyclerView.setAdapter(MainActivity.adapterMotif);


        //APPEL DE LA FONCTION
        queryMotif();

    }


    //--------------------------------------------------------------------------------------
    // ------------------------ clickAjoutCatalogue() ------------------------
    public void clickAjoutCatalogue(View v)
    {
        if(v.getId() == R.id.BtnAjouterCatalogue)
        {
            //Appeler une activité à démarrer = AJOUTER ACTIVITY
            Intent intent = new Intent(this, Activity_AjoutMotif.class);
            startActivity(intent);
            finish();
            }

    }


    // ------------------------ QUERYMOTIF() ------------------------
    protected void queryMotif()
    {
        InterfaceServeur interfaceServeur = RetrofitInstance.getInstance().create(InterfaceServeur.class);
        Call<ResponseBody> call = interfaceServeur.getMotifQuery();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<Motif> list = new ArrayList<>();


                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray jsonArray1 = jsonArray.getJSONArray(i);

                        int idMotif = jsonArray1.getInt(0);
                        int idType = jsonArray1.getInt(1);
                        int idUser = jsonArray1.getInt(2);
                        String dateCreation = jsonArray1.getString(3);
                        String source = jsonArray1.getString(4);
                        String nomMotif = jsonArray1.getString(5);
                        String imgCreation = jsonArray1.getString(6);

                        Motif m = new Motif(idMotif, idType, idUser, dateCreation, source, nomMotif, imgCreation);
                        list.add(m);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tabMotif(list);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tabMotif(new ArrayList<>());
            }
        });
    }

    //--------------------------------------------------------------------------------------
    //------------------ onCreateOptionsMenu() ------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);

        //Icône qui devienne enable dans la page active
        // Catalogue (0)
        menu.getItem(0).setEnabled(false);
        menu.getItem(0).getIcon().setAlpha(125);

        //Ajouter (1)
        menu.getItem(1).setEnabled(true);
        menu.getItem(1).getIcon().setAlpha(255);

        //Paramètre (2)
        menu.getItem(2).setEnabled(true);
        menu.getItem(2).getIcon().setAlpha(255);

        return true;
    }

    //--------------------------------------------------------------------------------------
    //------------------ onOptionsItemSelected() ------------------
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


    // ------------------------ GESTIONLONGCLIC() ------------------------
    @Override
    public void gestionLongClic(Motif m, int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        View view = View.inflate(this, R.layout.menu_dialog_affichage_information_motif,
                            null);
        builder.setView(view);

        AlertDialog alertDialog1 = builder.create();

        ImageView ImgMotif = view.findViewById(R.id.ImgMotifDialog);
        TextView TVNomMotif = view.findViewById(R.id.TVNomMotifDialog);
        TextView TVCreateurMotif = view.findViewById(R.id.TVCreateurDialog);
        TextView TVDateCreationMotif = view.findViewById(R.id.TVDateCreationDialog);

        Picasso.get().load(m.imgCreation).into(ImgMotif);
        TVNomMotif.setText(m.nomMotif);
        TVCreateurMotif.setText(m.source);
        TVDateCreationMotif.setText(m.dateCreation);

        ImageButton btClose= view.findViewById(R.id.btFermerDialogCatalogue);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });

        //Section Bouton Modifier / Supprimer
        Button btModifier = view.findViewById(R.id.btnModifierMotif);
        Button btnSupprimer = view.findViewById(R.id.btnSupprimerMotif);


        //Bouton Supprimer puis retour sur la page catalogue
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Catalogue.this);
                builder.setCancelable(false);

                View view = View.inflate(Activity_Catalogue.this, R.layout.menu_dialog_quitter_ajout, null);
                builder.setView(view);

                AlertDialog alertDialog2 = builder.create();

                //Attributs selon le contexte -
                Button btOui = view.findViewById(R.id.btnOuiDialog);
                Button btNon = view.findViewById(R.id.btnNonDialog);
                TextView tvTextBoite = view.findViewById(R.id.tvConfirmation);
                tvTextBoite.setText("Voulez-vous vraiment supprimer : " + m.nomMotif);

                //Bouton Non pour rester sur la page
                btNon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog2.dismiss();
                        alertDialog1.dismiss();
                    }
                });

                //Bouton Oui pour quitter et retourner à l'accueil
                btOui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog1.dismiss();
                        alertDialog2.dismiss();

                        //Supprimer le motif
                        InterfaceServeur serveur = RetrofitInstance.getInstance().create((InterfaceServeur.class));
                        Call<ResponseBody> call = serveur.supprimerMotif(m.getIdMotif(), m.getIdUser(), m.getIdType(),
                                m.getSource(), m.getDateCreation(), m.getNomMotif(), m.getImgCreation());

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String message = null;
                                try {
                                    MainActivity.adapterMotif.suprimerMotif(position);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                System.err.println(t);
                            }
                        });

                    }
                });

                alertDialog2.show();
            }
        });

        //Bouton Modifier pour aller sur la page
        btModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appeler une activité à démarrer = Modifier ACTIVITY
                Intent intent = new Intent(Activity_Catalogue.this, Activity_Modifier.class);

                intent.putExtra("idMotif", m.getIdMotif());
                intent.putExtra("idType", m.getIdType());
                intent.putExtra("idUser", m.getIdUser());
                intent.putExtra("source", m.getSource());
                intent.putExtra("nomMotif", m.getNomMotif());
                intent.putExtra("imgCreation", m.getImgCreation());
                intent.putExtra("dateCreation", m.getDateCreation());

                intent.putExtra("Index", position);

                startActivity(intent);
                alertDialog1.dismiss();
            }
        });


        alertDialog1.show();

    }


    // ------------------------ TABMOTIF() ------------------------
    protected void tabMotif(List<Motif> list)
    {
        //MainActivity.adapterMotif.clear();
        for (Motif m : list)
            MainActivity.adapterMotif.ajouterMotif(m);
    }
}