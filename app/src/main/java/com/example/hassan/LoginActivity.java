package com.example.hassan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private TextView sign_up_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();
        TextView editEmail =(TextView) findViewById(R.id.email);
        TextView editPassword =(TextView) findViewById(R.id.password);
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.LOGIN);
        sign_up_text = findViewById(R.id.SignUp);


        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email is required!",Toast.LENGTH_LONG).show();
                    return;}
                if(!Patterns. EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(LoginActivity.this,"Please enter a valid email!",Toast.LENGTH_LONG).show();
                    return;}
                if(password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Password is required!",Toast.LENGTH_LONG).show();
                    return;
                }
                signIn(email, password);
            }
        });

    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this , MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}