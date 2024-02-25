package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
   ImageButton imgbtn;
   private TextInputLayout textInput;
    private TextInputLayout mailInput;
    private TextInputLayout pswdInput;
    private TextInputLayout cfmrpswdInput;
    private TextInputLayout mobileInput;
  ImageView img;
    Animation top;
    //EditText name,mail,password,confirm,mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgbtn = (ImageButton) findViewById(R.id.log);
        //Hooks
        textInput = findViewById(R.id.username);
        mailInput = findViewById(R.id.email);
        pswdInput = findViewById(R.id.password);
        cfmrpswdInput = findViewById(R.id.confirmpassword);
        mobileInput = findViewById(R.id.mobile);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_Data();
            }
        });
    }
        
        private void validate_Data()
        {
            String name=textInput.getEditText().getText().toString().trim();
            String email=mailInput.getEditText().getText().toString();
            String pswd=pswdInput.getEditText().getText().toString();
            String cfmr=cfmrpswdInput.getEditText().getText().toString();
            String mnum=mobileInput.getEditText().getText().toString();

            //Login Sound
            final MediaPlayer media=MediaPlayer.create(this,R.raw.amw);
            
            if(name.isEmpty()) {
                textInput.setError("Field can't be empty..");
            } else if (!name.matches("^[a-zA-Z]+")) {
                textInput.setError("Numbers are not Allowed!!");
            } else if (name.length()<3) {
                textInput.setError("Length should be atleast 3");
            }
            else {
              textInput.setError(null);
            }
            
            if(email.isEmpty()) {
            mailInput.setError("Field can't be empty..");
            } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                mailInput.setError("Invalid mail...");
            }
            else
            {
                mailInput.setError(null);
            }
            
            if(pswd.isEmpty())
            {
                pswdInput.setError("Field can't be empty..");
            } else if (pswd.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                pswdInput.setError("Password is Strong");
            } else if (pswd.length()<6) {
                pswdInput.setError("Password length should be atleast 6");
            }
            else {
                pswdInput.setError(null);
            }

            if(cfmr.isEmpty()) {
             cfmrpswdInput.setError("Field can't be empty..");
            } else if (!cfmr.equals(pswd)) {
                cfmrpswdInput.setError("password does not match with original one");
            }
            else {
              cfmrpswdInput.setError(null);
            }

            if(mnum.isEmpty())
            {
                mobileInput.setError("Field can't be empty..");
            } else if (!mnum.matches("^[6-9][0-9]{9}$")) {
                mobileInput.setError("Invalid mobile number...");
            }  else {
             mobileInput.setError(null);
            }

            if(textInput.getError()==null && mailInput.getError()==null && pswdInput.getError()==null && cfmrpswdInput.getError()==null && mobileInput.getError()==null) {
            Toast.makeText(this,"Log -In Successfull !!!!",Toast.LENGTH_LONG).show();
            media.start();
            }
        }
}