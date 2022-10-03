package com.yildirimtechnologies.basitsosyalmedyaplatformtaslagi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.rpc.context.AttributeContext;
import com.yildirimtechnologies.basitsosyalmedyaplatformtaslagi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(MainActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void girmek (View view){
        String email = binding.EmailText.getText().toString();
        String Password = binding.PasswordText.getText().toString();
        if (email.equals("") || Password.equals("")){
            Toast.makeText(this, "E-mailinizi ve ya Şifrenizi Girmelisiniz", Toast.LENGTH_SHORT).show();
        }else {
            auth.signInWithEmailAndPassword(email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }
            });
        }



    }

    public void kayit (View view){
        String email = binding.EmailText.getText().toString();
        String Password = binding.PasswordText.getText().toString();
        if (email.equals("") || Password.equals("")){
            Toast.makeText(this, "E-mailinizi ve ya Şifrenizi Girmelisiniz", Toast.LENGTH_SHORT).show();
        }else {
            auth.createUserWithEmailAndPassword(email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}