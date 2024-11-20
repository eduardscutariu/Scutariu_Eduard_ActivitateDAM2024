package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaElicoptere extends AppCompatActivity {
    private int idModificat=0;
    private ElicopterAdapter adapter=null;
     private List<Elicopter> elicoptere=null;

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
        elicoptere=it.getParcelableArrayListExtra("elicoptere");
        if (elicoptere == null) {
            Toast.makeText(this, "Lista de elicoptere este goală sau nu a fost primită corect.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, elicoptere.toString(), Toast.LENGTH_SHORT).show();
        }
        ListView lv=findViewById(R.id.lista);

        

        //ArrayAdapter<Elicopter> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,elicoptere);
        adapter=new ElicopterAdapter(elicoptere,getApplicationContext(), R.layout.row_item);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intentModifica=new Intent(getApplicationContext(),AdaugareElicopter.class);
                intentModifica.putExtra("elicopter",elicoptere.get(position));
                idModificat=position;
                startActivityForResult(intentModifica,209);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==209)
        {
            elicoptere.set(idModificat,data.getParcelableExtra("elicopter"));
            adapter.notifyDataSetChanged();
        }
    }
}