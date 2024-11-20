package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiElicoptere extends AppCompatActivity {

    private List<Bitmap> imagini;
    private List<Imagine> listaImagini;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_elicoptere);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://s.iw.ro/gateway/g/ZmlsZVNvdXJjZT1odHRwJTNBJTJGJTJG/c3RvcmFnZTA4dHJhbnNjb2Rlci5yY3Mt/cmRzLnJvJTJGc3RvcmFnZSUyRjIwMjQl/MkYwMiUyRjA3JTJGMTg5ODI0Ml8xODk4/MjQyX2NvdWdhci1lbGljb3B0ZXItYWly/YnVzLmpwZyZ3PTc4MCZoPTQ0MCZoYXNo/PTJlZjUxMWQzZGYwY2EzMDc2ZmFkMGJiOTgxNjU5YzIx.thumb.jpg");
        linkuriImagini.add("https://media.dcnews.ro/image/202107/w670/elicopter-militar-incidente_24405500.jpg");
        linkuriImagini.add("https://media.defenseromania.ro/image/202104/w670/h215m-chile_89249900.jpg");
        linkuriImagini.add("https://img.observatornews.ro/0/2022/10/26/494600/airbus-h215m-f298e8b2.jpg?w=1200");
        linkuriImagini.add("https://umbrela-strategica.ro/wp-content/uploads/2021/04/iarLEBANESEAIRFORCE.jpg");



        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini)
                {
                    HttpURLConnection con=null;
                    try {
                        URL url=new URL(link);
                        con =(HttpURLConnection) url.openConnection();
                        InputStream is=con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                listaImagini=new ArrayList<>();
                listaImagini.add(new Imagine("Elicopter 1",imagini.get(0),"https://ro.wikipedia.org/wiki/Elicopter"));
                listaImagini.add(new Imagine("Elicopter 2",imagini.get(1),"https://ro.wikipedia.org/wiki/Elicopter"));
                listaImagini.add(new Imagine("Elicopter 3",imagini.get(2),"https://ro.wikipedia.org/wiki/Elicopter"));
                listaImagini.add(new Imagine("Elicopter 4",imagini.get(3),"https://ro.wikipedia.org/wiki/Elicopter"));
                listaImagini.add(new Imagine("Elicopter 5",imagini.get(4),"https://ro.wikipedia.org/wiki/Elicopter"));
            }
        });

        ListView lw=findViewById(R.id.listaImagini);
        adapter=new ImageAdapter(listaImagini,getApplicationContext(), R.layout.row_image);
        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(getApplicationContext(), ImaginiElicoptere.class);
                it.putExtra("link",listaImagini.get(i).getLink());
                startActivity(it);
            }
        });

    }


}