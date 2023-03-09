package com.example.projetapplicationmobilemarkus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

public class Activity_Parametres extends AppCompatActivity {

    //DÉCLARATIONS
//    private ImageView qrCodeIV;
//    private EditText dataEdt;
//    private Button generateQrBtn;
//    Bitmap bitmap;
//    Activity_Parametres qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

//        // initializing all variables.
//        qrCodeIV = findViewById(R.id.idIVQrcode);
//        dataEdt = findViewById(R.id.idEdt);
//        generateQrBtn = findViewById(R.id.idBtnGenerateQR);
//
//        // initializing onclick listener for button.
//        generateQrBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(dataEdt.getText().toString())) {
//
//                    // if the edittext inputs are empty then execute
//                    // this method showing a toast message.
//                    Toast.makeText(Activity_Parametres.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
//                } else {
//                    // below line is for getting
//                    // the windowmanager service.
//                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
//
//                    // initializing a variable for default display.
//                    Display display = manager.getDefaultDisplay();
//
//                    // creating a variable for point which
//                    // is to be displayed in QR Code.
//                    Point point = new Point();
//                    display.getSize(point);
//
//                    // getting width and
//                    // height of a point
//                    int width = point.x;
//                    int height = point.y;
//
//                    // generating dimension from width and height.
//                    int dimen = width < height ? width : height;
//                    dimen = dimen * 3 / 4;
//
//                    // setting this dimensions inside our qr code
//                    // encoder to generate our qr code.
//                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen);
//                    try {
//                        // getting our qrcode in the form of bitmap.
//                        bitmap = qrgEncoder.encodeAsBitmap();
//                        // the bitmap is set inside our image
//                        // view using .setimagebitmap method.
//                        qrCodeIV.setImageBitmap(bitmap);
//                    } catch (WriterException e) {
//                        // this method is called for
//                        // exception handling.
//                        Log.e("Tag", e.toString());
//                    }
//                }
//            }
//        });
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
        menu.getItem(0).setEnabled(true);
        menu.getItem(0).getIcon().setAlpha(255);

        //Ajouter (1)
        menu.getItem(1).setEnabled(true);
        menu.getItem(1).getIcon().setAlpha(255);

        //Paramètre (2)
        menu.getItem(2).setEnabled(false);
        menu.getItem(2).getIcon().setAlpha(125);
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
//                Intent intent = new Intent(this, Activity_Parametres.class);
//                startActivity(intent);
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

}

