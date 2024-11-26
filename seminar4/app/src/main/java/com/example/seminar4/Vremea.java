package com.example.seminar4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Vremea extends AppCompatActivity {
    private int nrZile;

    TemperaturaAdapter adapter;
    List<Pair<Double,Double>> temperaturi=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vremea);



        EditText oras = findViewById(R.id.editTextText);
        TextView cheia = findViewById(R.id.textView4);
        TextView maxmin = findViewById(R.id.textView6);
        Button btnCautare = findViewById(R.id.button5);
        Spinner zi=findViewById(R.id.spinner);



        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        btnCautare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orasCautat = oras.getText().toString();
                if (orasCautat.isEmpty()) {
                    cheia.setText("introdu oras");
                    return;
                }

                nrZile = Integer.parseInt(zi.getSelectedItem().toString());


                String searchUrl = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=r6LT2oKgDflztukLUphZ6nbL9GceGGhl&q=" + orasCautat;

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(searchUrl);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");

                            int responseCode = con.getResponseCode();
                            if (responseCode == 200) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                StringBuilder response = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    response.append(line);
                                }
                                reader.close();

                                JSONArray jsonResponse = new JSONArray(response.toString());
                                if (jsonResponse.length() > 0) {
                                    JSONObject firstCity = jsonResponse.getJSONObject(0);
                                    String cityKey = firstCity.getString("Key");

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            cheia.setText("Key: " + cityKey);
                                        }
                                    });

                                    String forecastUrl = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/" + cityKey + "?apikey=r6LT2oKgDflztukLUphZ6nbL9GceGGhl&metric=true";
                                    getWeatherForecast(forecastUrl, maxmin, executor, handler);
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(Vremea.this, "eroare", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getWeatherForecast(String forecastUrl, TextView maxmin, Executor executor, Handler handler) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(forecastUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    if (responseCode == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONArray dailyForecasts = jsonResponse.getJSONArray("DailyForecasts");
                        if (dailyForecasts.length() > 0) {
                            for (int i=0;i< dailyForecasts.length();i++)
                            {
                                JSONObject forecast = dailyForecasts.getJSONObject(i);
                                JSONObject temperature = forecast.getJSONObject("Temperature");
                                double minTemp = temperature.getJSONObject("Minimum").getDouble("Value");
                                double maxTemp = temperature.getJSONObject("Maximum").getDouble("Value");

                                temperaturi.add(new Pair<>(minTemp,maxTemp));
                            }


                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    maxmin.setText("max: " + temperaturi.get(0).first.toString() + "°C\nmin: " + temperaturi.get(0).second.toString() + "°C");
                                }
                            });

                            handler.post(() -> {
                                maxmin.setText("Max: " + temperaturi.get(0).second + "°C\nMin: " + temperaturi.get(0).first + "°C");
                                adapter = new TemperaturaAdapter(getApplicationContext(), R.layout.row_vreme, temperaturi.subList(0,nrZile));
                                ListView listaZile = findViewById(R.id.listaZile);
                                listaZile.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            });


                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(Vremea.this, "eroare", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
