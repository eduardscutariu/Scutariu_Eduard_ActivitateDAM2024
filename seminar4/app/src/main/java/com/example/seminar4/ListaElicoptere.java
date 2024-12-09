package com.example.seminar4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaElicoptere extends AppCompatActivity {
    private int idModificat = -1;
    private ElicopterAdapter adapter;
    private List<Elicopter> elicoptere;
    private ElicopterDatabase db;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elicoptere);

        db = Room.databaseBuilder(getApplicationContext(), ElicopterDatabase.class, "TabelElicoptere").build();

        executor.execute(() -> {
            elicoptere = db.elicopterDAO().getAll();
            runOnUiThread(this::updateListView);
        });

        ListView lv = findViewById(R.id.lista);

        lv.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intentModifica = new Intent(getApplicationContext(), AdaugareElicopter.class);
            intentModifica.putExtra("elicopter", elicoptere.get(position));
            idModificat = position;
            startActivityForResult(intentModifica, 209);
        });

//        lv.setOnItemLongClickListener((adapterView, view, position, id) -> {
//            executor.execute(() -> {
//                db.elicopterDAO().delete(elicoptere.get(position));
//                elicoptere.remove(position);
//                runOnUiThread(() -> adapter.notifyDataSetChanged());
//            });
//            return true;
//        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences sp= getSharedPreferences("obiecte",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString(elicoptere.get(i).getKey(),elicoptere.get(i).toString());
                editor.commit();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 209 && data != null) {
            Elicopter elicopterModificat = data.getParcelableExtra("elicopter");
            if (elicopterModificat != null) {
                executor.execute(() -> {
                    db.elicopterDAO().update(elicopterModificat);
                    elicoptere.set(idModificat, elicopterModificat);
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                });
            }
        }
    }

    private void updateListView() {
        ListView lv = findViewById(R.id.lista);
        adapter = new ElicopterAdapter(elicoptere, this, R.layout.row_item);
        lv.setAdapter(adapter);
    }
}
