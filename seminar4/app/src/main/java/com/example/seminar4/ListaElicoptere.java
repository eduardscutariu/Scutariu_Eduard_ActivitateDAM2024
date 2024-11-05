package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaElicoptere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_elicoptere);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it=getIntent();
        List<Elicopter> elicoptere=it.getParcelableArrayListExtra("elicoptere");
        if (elicoptere == null) {
            Toast.makeText(this, "Lista de elicoptere este goală sau nu a fost primită corect.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, elicoptere.toString(), Toast.LENGTH_SHORT).show();
        }
        ListView lv=findViewById(R.id.lista);

        ArrayAdapter<Elicopter> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,elicoptere);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),elicoptere.get(position).toString(),Toast.LENGTH_LONG).show();

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                elicoptere.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}