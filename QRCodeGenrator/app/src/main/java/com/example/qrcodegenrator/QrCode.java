package com.example.qrcodegenrator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class QrCode extends AppCompatActivity {

    ImageView img;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;

    Button btn,gener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        img=(ImageView) findViewById(R.id.res_qr);
        btn=(Button) findViewById(R.id.back);
        gener=(Button) findViewById(R.id.generate);
        countdownTextView=(TextView)findViewById(R.id.counter);

        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update UI with the remaining time in seconds
                long secondsRemaining = millisUntilFinished / 1000;
                countdownTextView.setText("Expires in : " + secondsRemaining + " seconds");
            }

            @Override
            public void onFinish() {
                // Perform actions when the countdown finishes
                countdownTextView.setText("QRCode Expired");
                img.setImageDrawable(null);
            }
        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QrCode.this,MainActivity.class);
                startActivity(i);
            }
        });

        gener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data="Vikki";
                try {
                    Bitmap qrcode=generaetQRcode(data,1000,1000);
                    img.setImageBitmap(qrcode);
                    // Start the countdown timer
                    countDownTimer.start();

                }
                catch (WriterException e) {
                    e.printStackTrace();
                    // Handle the exception here, e.g., show an error message
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the countdown timer to prevent memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

//    //Function to set Validity
//
//    private String generateQRCodeDataWithExpiry(String content, int expiryMinutes) {
//        // Add expiry time to the content
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String expiryTimeString = sdf.format(new Date(System.currentTimeMillis() + (expiryMinutes * 60 * 1000))); // Calculate expiry time
//        return content + ";" + expiryTimeString;
//    }



    private Bitmap generaetQRcode(String data,int width,int height) throws WriterException
    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

        int matrixWidth = bitMatrix.getWidth();
        int matrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[matrixWidth * matrixHeight];

        for (int y = 0; y < matrixHeight; y++) {
            int offset = y * matrixWidth;
            for (int x = 0; x < matrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, matrixWidth, 0, 0, matrixWidth, matrixHeight);

        return bitmap;

    }
}