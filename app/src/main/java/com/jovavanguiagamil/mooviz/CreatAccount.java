package com.jovavanguiagamil.mooviz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreatAccount extends AppCompatActivity {

    private EditText username, email, password;
    private Button register;
    private TextView txt_login;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_account);

        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        txt_login = findViewById(R.id.txt_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatAccount.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(CreatAccount.this);
                pd.setMessage("Please wait");
                pd.show();

                String str_username = username.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_username)) {
                    Toast.makeText(CreatAccount.this, "Username is empty", Toast.LENGTH_SHORT).show();
                }
                
                if (TextUtils.isEmpty(str_email)){
                    Toast.makeText(CreatAccount.this, "Email is empty", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(str_password)){
                    Toast.makeText(CreatAccount.this, "Password is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(str_username, str_email, str_password);
                }
                

            }
        });


    }

    private void register(final String username, String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username.toLowerCase());

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        pd.dismiss();
                                        Intent intent = new Intent(CreatAccount.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }
                            });
                        }else {
                            pd.dismiss();
                            Toast.makeText(CreatAccount.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
