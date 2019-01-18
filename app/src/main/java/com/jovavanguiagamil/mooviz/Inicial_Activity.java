package com.jovavanguiagamil.mooviz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Inicial_Activity extends Activity {
    private Button inicial;
    private FirebaseAuth mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        mFirebase = FirebaseAuth.getInstance();

        inicial = (Button) findViewById(R.id.telainicial);
        inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(Inicial_Activity.this, Login.class);
                startActivity(inicio);
            }
        });



    }



    private void menuInicial(){
        Intent inicio = new Intent(Inicial_Activity.this, MainActivity.class);
        startActivity(inicio);
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebase.getCurrentUser();
        if (currentUser != null) {
            menuInicial();
        }

    }
}
