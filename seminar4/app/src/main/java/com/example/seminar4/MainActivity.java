package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Elicopter> elicoptere=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        elicoptere=new ArrayList<>();


        Button btn=findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(), AdaugareElicopter.class);
                startActivityForResult(it,403);
            }
        });

        Button btnLista=findViewById(R.id.button2);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(getApplicationContext(),ListaElicoptere.class);
                it.putParcelableArrayListExtra("elicoptere",(ArrayList<? extends Parcelable>) elicoptere);
                startActivity(it);
            }
        });

        Button btnImagini=findViewById(R.id.button3);
        btnImagini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(getApplicationContext(),ImaginiElicoptere.class);
                startActivity(it);
            }
        });

        Button btnVreme=findViewById(R.id.button4);
        btnVreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),Vremea.class);
                startActivity(it);
            }
        });

        Button btnSharedPref=findViewById(R.id.button6);
        btnSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(getApplicationContext(), ListaSharedPreferences.class);
                startActivity(it);
            }
        });



        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dam-14ian-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference referece = database.getReference();
        referece.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "Baza de date actualizata", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==403)
        {
            if(resultCode==RESULT_OK)
            {
                Elicopter elicopter=data.getParcelableExtra("elicopter");
                elicoptere.add(elicopter);
                Toast.makeText(getApplicationContext(), elicopter.toString(), Toast.LENGTH_SHORT).show();
            }
        }


    }


}