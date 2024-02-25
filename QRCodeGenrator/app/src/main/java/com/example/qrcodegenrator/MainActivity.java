package com.example.qrcodegenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtview;
    Button btn;
    private  Animation top,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        txtview=(TextView) findViewById(R.id.txt);
        btn=(Button) findViewById(R.id.btn);

        txtview.setAnimation(top);
        btn.setAnimation(bottom);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQRcode();
            }
        });

    }

    private void generateQRcode()
    {
        Intent i=new Intent(MainActivity.this,QrCode.class);
        startActivity(i);
    }
}
