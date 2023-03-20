package com.example.projetapplicationmobilemarkus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Activity_Parametres extends AppCompatActivity {

    //DÉCLARATIONS
    SurfaceView SVScanner;
    TextureView textureView;

    private ImageView qrCodeIV;
    Bitmap bitmap;
    //QRGEncoder qrgEncoder;
    Button btnScanQrCode;

    private CameraManager cameraManager;
    private String cameraId;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSession;
    private SurfaceTexture surfaceTexture;
    private Surface previewSurface;
    private CaptureRequest.Builder previewBuilder;
    private CaptureRequest previewRequest;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        // Trouvez nos champs dans le XML
//        btnScanQrCode = findViewById(R.id.btnScannerCamera);
//        qrCodeIV = findViewById(R.id.idIVQrcode);
//        textureView = findViewById(R.id.textureView);
//        SVScanner = findViewById(R.id.SVCameraLecteur);

//        generateQRCode("https://www.google.ca/?hl=fr");
//
//        btnScanQrCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startQRScanner();
//            }
//        });
    }


    //--------------------------------------------------------------------------------------
    //------------------ GENERATEQRCODE() ------------------
//    private void generateQRCode(String data) {
//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512);
//        int width = bitMatrix.getWidth();
//        int height = bitMatrix.getHeight();
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white));
//            }
//        }
////        showQRCode(bitmap);
//    } catch (WriterException e) {
//        e.printStackTrace();
//    }
//}
//
//
//    //--------------------------------------------------------------------------------------
//    //------------------ SHOWQRCODE() ------------------
////    private void showQRCode(Bitmap bitmap) {
////        SurfaceHolder holder = imgCodeQR.getHolder();
////        holder.addCallback(new SurfaceHolder.Callback() {
////            @Override
////            public void surfaceCreated(SurfaceHolder holder) {
////                Canvas canvas = holder.lockCanvas();
////                canvas.drawBitmap(bitmap, 0, 0, null);
////                holder.unlockCanvasAndPost(canvas);
////            }
////
////            @Override
////            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
////
////            }
////
////            @Override
////            public void surfaceDestroyed(SurfaceHolder holder) {
////
////            }
////        });
////    }
//
//
//
//
//    private void startQRScanner() {
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setOrientationLocked(false);
//        integrator.setPrompt("Scannez un code QR");
//        integrator.setBeepEnabled(true);
//        integrator.initiateScan();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IntentIntegrator.REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                String contents = data.getStringExtra(Intents.Scan.RESULT);
//                // Utilisez le contenu du code QR ici.
//                System.out.println("OKKKK ----------------------");
//
//
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // Le scanner a été annulé.
//                System.out.println("ERREUR ----------------------");
//
//
//            }
//        }
//    }


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

