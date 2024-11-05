package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class AdaugareElicopter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_elicopter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent itGet=getIntent();
        if(itGet.hasExtra("elicopter"))
        {
            Elicopter elicopterG=itGet.getParcelableExtra("elicopter");

            EditText etNumeProducatorG=findViewById(R.id.numeProducator);
            EditText etPretG=findViewById(R.id.pret);
            EditText etAutonomieG=findViewById(R.id.autonomie);
            EditText etNumarLocuriG=findViewById(R.id.numarLocuri);
            Spinner spTipG=findViewById(R.id.tip);
            DatePicker dpG= findViewById(R.id.data);

            etNumeProducatorG.setText(elicopterG.getProducator());
            etPretG.setText(String.valueOf(elicopterG.getPret()));
            etAutonomieG.setText(String.valueOf(elicopterG.getAutonomie_Mile()));
            etNumarLocuriG.setText(String.valueOf(elicopterG.getNumarLocuri()));
            spTipG.setSelection(elicopterG.isNou() ? 0:1);
            dpG.init(elicopterG.getDataFabricatiei().getYear(),
                    elicopterG.getDataFabricatiei().getMonth(),
                    elicopterG.getDataFabricatiei().getDay(),
                    null);

            Toast.makeText(this, dpG.toString(), Toast.LENGTH_SHORT).show();


        }

        Button salvareBtn=findViewById(R.id.button);
        salvareBtn.setOnClickListener((view) ->
        {
            EditText etNumeProducator=findViewById(R.id.numeProducator);
            String numeProducator=etNumeProducator.getText().toString();

            EditText etPret=findViewById(R.id.pret);
            float pret= Float.parseFloat((etPret.getText().toString()));

            EditText etAutonomie=findViewById(R.id.autonomie);
            float autonomie= Float.parseFloat(etAutonomie.getText().toString());

            EditText etNumarLocuri=findViewById(R.id.numarLocuri);
            int numarLocuri= Integer.parseInt(etNumarLocuri.getText().toString());

            Spinner spTip=findViewById(R.id.tip);
            boolean nou;
            nou= spTip.getSelectedItem().toString().contentEquals("nou");

            DatePicker dp= findViewById(R.id.data);
            Date data=new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
            Elicopter elicopter=new Elicopter(numeProducator,pret,autonomie,numarLocuri,data,nou);


            Intent it=new Intent();
            it.putExtra("elicopter",elicopter);
            setResult(RESULT_OK,it);
            finish();


        });


    }
}