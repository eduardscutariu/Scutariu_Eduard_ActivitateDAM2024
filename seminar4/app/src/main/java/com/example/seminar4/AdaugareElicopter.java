package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AdaugareElicopter extends AppCompatActivity {

    private Elicopter elicopterExist;
    private EditText etNumeProducator;
    private EditText etPret;
    private EditText etAutonomie;
    private EditText etNumarLocuri;
    private Spinner spTip;
    private DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_elicopter);

        etNumeProducator = findViewById(R.id.numeProducator);
        etPret = findViewById(R.id.pret);
        etAutonomie = findViewById(R.id.autonomie);
        etNumarLocuri = findViewById(R.id.numarLocuri);
        spTip = findViewById(R.id.tip);
        dp = findViewById(R.id.data);

        Intent intent = getIntent();
        if (intent.hasExtra("elicopter")) {
            elicopterExist = intent.getParcelableExtra("elicopter");
            populateFields(elicopterExist);
        }

        Button salvareBtn = findViewById(R.id.button);
        salvareBtn.setOnClickListener(view -> saveElicopter());
    }

    private void populateFields(Elicopter elicopter) {
        etNumeProducator.setText(elicopter.getProducator());
        etPret.setText(String.valueOf(elicopter.getPret()));
        etAutonomie.setText(String.valueOf(elicopter.getAutonomie_Mile()));
        etNumarLocuri.setText(String.valueOf(elicopter.getNumarLocuri()));
        spTip.setSelection(elicopter.isNou() ? 0 : 1);

        Date dataFabricatie = elicopter.getDataFabricatiei();
        dp.updateDate(dataFabricatie.getYear() , dataFabricatie.getMonth(), dataFabricatie.getDate());
    }

    private void saveElicopter() {
        String numeProducator = etNumeProducator.getText().toString();
        float pret = Float.parseFloat(etPret.getText().toString());
        float autonomie = Float.parseFloat(etAutonomie.getText().toString());
        int numarLocuri = Integer.parseInt(etNumarLocuri.getText().toString());
        boolean nou = spTip.getSelectedItem().toString().equals("nou");
        Date dataFabricatie = new Date(dp.getYear() , dp.getMonth(), dp.getDayOfMonth());

        if (elicopterExist != null) {
            elicopterExist.setProducator(numeProducator);
            elicopterExist.setPret(pret);
            elicopterExist.setAutonomie_Mile(autonomie);
            elicopterExist.setNumarLocuri(numarLocuri);
            elicopterExist.setNou(nou);
            elicopterExist.setDataFabricatiei(dataFabricatie);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("elicopter", elicopterExist);
            setResult(RESULT_OK, resultIntent);
        } else {
            Elicopter elicopterNou = new Elicopter(numeProducator, pret, autonomie, numarLocuri, dataFabricatie, nou);

            new Thread(() -> {
                ElicopterDatabase.getInstance(this).elicopterDAO().insert(elicopterNou);
                runOnUiThread(() -> Toast.makeText(this, "Elicopter salvat!", Toast.LENGTH_SHORT).show());
            }).start();
        }
        finish();
    }
}
