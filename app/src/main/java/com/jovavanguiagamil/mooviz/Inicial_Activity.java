package com.jovavanguiagamil.mooviz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Inicial_Activity extends Activity {
    private Button inicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        inicial = (Button) findViewById(R.id.telainicial);
        inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(Inicial_Activity.this, Login.class);
                startActivity(inicio);
            }
        });



    }
}
