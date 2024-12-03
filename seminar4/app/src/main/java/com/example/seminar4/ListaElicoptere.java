package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class ListaElicoptere extends AppCompatActivity {
    private int idModificat = -1;
    private ElicopterAdapter adapter;
    private List<Elicopter> elicoptere;
    private ElicopterDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elicoptere);

        db = Room.databaseBuilder(getApplicationContext(), ElicopterDatabase.class, "TabelElicoptere").build();

        new Thread(() -> {
            elicoptere = db.elicopterDAO().getAll();
            runOnUiThread(this::updateListView);
        }).start();

        ListView lv = findViewById(R.id.lista);

        lv.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intentModifica = new Intent(getApplicationContext(), AdaugareElicopter.class);
            intentModifica.putExtra("elicopter", elicoptere.get(position));
            idModificat = position;
            startActivityForResult(intentModifica, 209);
        });

        lv.setOnItemLongClickListener((adapterView, view, position, id) -> {
            new Thread(() -> {
                db.elicopterDAO().delete(elicoptere.get(position));
                elicoptere.remove(position);
                runOnUiThread(() -> adapter.notifyDataSetChanged());
            }).start();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 209 && data != null) {
            Elicopter elicopterModificat = data.getParcelableExtra("elicopter");
            if (elicopterModificat != null) {
                new Thread(() -> {
                    db.elicopterDAO().update(elicopterModificat);
                    elicoptere.set(idModificat, elicopterModificat);
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                }).start();
            }
        }
    }
    private void updateListView() {
        ListView lv = findViewById(R.id.lista);
        adapter = new ElicopterAdapter(elicoptere, this, R.layout.row_item);
        lv.setAdapter(adapter);
    }
}
