package com.example.projetapplicationmobilemarkus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;

import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Intents;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Arrays;

public class Activity_Parametres extends AppCompatActivity {

    //DÉCLARATIONS
//    private ImageView qrCodeIV;
//    private EditText dataEdt;
//    private Button generateQrBtn;
//    Bitmap bitmap;
//    Activity_Parametres qrgEncoder;

    SurfaceView SVScanner;
    TextureView textureView;
    Button btnScanQrCode;

    ImageView imgCodeQR;


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

        // Trouvez votre bouton dans votre mise en page
        btnScanQrCode = findViewById(R.id.btnScannerCamera);

        textureView = findViewById(R.id.textureView);
        SVScanner = findViewById(R.id.SVCameraLecteur);

        generateQRCode("Text à encoder");

        SVScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });
    }


    //--------------------------------------------------------------------------------------
    //------------------ GENERATEQRCODE() ------------------
    private void generateQRCode(String data) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white));
            }
        }
        showQRCode(bitmap);
    } catch (WriterException e) {
        e.printStackTrace();
    }
}


    //--------------------------------------------------------------------------------------
    //------------------ SHOWQRCODE() ------------------
    private void showQRCode(Bitmap bitmap) {
        SurfaceHolder holder = imgCodeQR.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawBitmap(bitmap, 0, 0, null);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }




    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scannez un code QR");
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra(Intents.Scan.RESULT);
                // Utilisez le contenu du code QR ici.



            } else if (resultCode == RESULT_CANCELED) {
                // Le scanner a été annulé.



            }
        }
    }




















//        SurfaceHolder surfaceHolder = SVScanner.getHolder();
//        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                // La surface est créée. Vous pouvez initialiser la caméra ici.
//
//
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                // La surface a changé de taille ou de format. Vous pouvez ajuster la caméra ici.
//
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                // La surface est détruite. Vous devez libérer la caméra ici.
//
//
//            }
//        });

//
//        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        try {
//            cameraId = cameraManager.getCameraIdList()[0];
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }


        // Ajoutez un écouteur de clic de bouton
//        btnScanQrCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Appelez la méthode pour ouvrir la caméra et scanner le code QR
//                openCameraAndScanQRCode();
//            }
//        });

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


//    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
//        @Override
//        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
//            Activity_Parametres.this.surfaceTexture = surfaceTexture;
//            // La surface est prête à être utilisée. La caméra peut être ouverte ici.
//        }
//
//        @Override
//        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
//
//        }
//
//        @Override
//        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
//            return false;
//        }
//
//        @Override
//        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
//
//        }
//    };


//    //--------------------------------------------------------------------------------------
//    //------------------ openCameraAndScanQRCode() ------------------
//    private void openCameraAndScanQRCode() {
////        // Vérifiez si la permission de caméra est accordée
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////            // Si la permission n'est pas accordée, demandez-la à l'utilisateur
////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
////            return;
////        }
////
////        try {
////            cameraManager.openCamera(cameraId, cameraDeviceCallback, null);
////        } catch (CameraAccessException e) {
////            e.printStackTrace();
////        }
//
//        // Méthode pour ouvrir la caméra et scanner le code QR
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Integer.parseInt(CAMERA_SERVICE));
//            return;
//        }
//
//        try {
//            cameraManager.openCamera(cameraId, new CameraDevice.StateCallback() {
//                @Override
//                public void onOpened(CameraDevice cameraDevice) {
//                    // La caméra est ouverte, vous pouvez afficher l'aperçu de la caméra
//                }
//
//                @Override
//                public void onDisconnected(CameraDevice cameraDevice) {
//                    cameraDevice.close();
//                }
//
//                @Override
//                public void onError(CameraDevice cameraDevice, int error) {
//                    cameraDevice.close();
//                }
//            }, null);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//
//        // Déclaration de la variable cameraDeviceCallback
//        CameraDevice.StateCallback cameraDeviceCallback = new CameraDevice.StateCallback() {
//            @Override
//            public void onOpened(CameraDevice cameraDevice) {
//                // La caméra est ouverte, vous pouvez afficher l'aperçu de la caméra
//            }
//
//            @Override
//            public void onDisconnected(CameraDevice cameraDevice) {
//                cameraDevice.close();
//            }
//
//            @Override
//            public void onError(CameraDevice cameraDevice, int error) {
//                cameraDevice.close();
//            }
//        };
//
//    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                openCameraAndScanQRCode();
//            } else {
//                Toast.makeText(this, "La permission d'accéder à la caméra a été refusée", Toast.LENGTH_SHORT).show();
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

