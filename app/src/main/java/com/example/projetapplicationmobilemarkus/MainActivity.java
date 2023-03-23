package com.example.projetapplicationmobilemarkus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
           INFORMATIONS DE CONNEXION
           USER : admin
           PASSWORD : admin
    */

    //DÉCLARATIONS
    EditText ETNomUser, ETPwd;
    TextView TVMessageErreur;
    Button BTNConnexion;

    static AdapterMotif adapterMotif;

    ImageButton imgButtonAjouter, imgButtonCatalogue;

    //--------------------------------------------------------------------------------------
    // ONCREATE() ---------------------------------------------
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new fragment_login());
        pagerAdapter.addFragmet(new fragment_register());
        viewPager.setAdapter(pagerAdapter);

        //--------------------------------------------------------------------------------------
        // AJOUTER UN MOTIF VS VISUALISER LE CATALOGUE ---------------------------------------------

        imgButtonCatalogue = findViewById(R.id.ImgButtonCatalogueAccueil);
        imgButtonAjouter = findViewById(R.id.ImgButtonAjouterAccueil);

        //VISUALISER LE CATALOGUE
        imgButtonCatalogue.setOnClickListener((view ->
        {
            Intent intent = new Intent(this, Activity_Catalogue.class);
            startActivity(intent);
        }));

        //AJOUTER UN MOTIF
        imgButtonAjouter.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, Activity_AjoutMotif.class);
            startActivity(intent);
        });

        MainActivity.adapterMotif = new AdapterMotif(new ArrayList<>());
    }



    //--------------------------------------------------------------------------------------
    // ONCREATEOPTIONSMENU() ---------------------------------------------
    @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
        }

                //--------------------------------------------------------------------------------------
                // ONOPTIONITEMSELECTED ---------------------------------------------
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
                        return true;
                    }

                    //BOUTON CATALOGUE (CATALOGUE)
                    case R.id.ItmCatalogue:
                    {
                        //Appeler une activité
                        Intent intent = new Intent(this, Activity_Catalogue.class);
                        startActivity(intent);
                        return true;
                    }

                    //BOUTON AJOUTER (AJOUTER)
                    case R.id.ItmAjouterMotif:
                    {
                        //Appeler une activité
                        Intent intent = new Intent(this, Activity_AjoutMotif.class);
                        startActivity(intent);
                        return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //--------------------------------------------------------------------------------------
    // AUTHENTICATIONPAGERADAPTER() ---------------------------------------------
    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
  }
