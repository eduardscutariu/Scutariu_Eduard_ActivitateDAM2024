package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class ElicopterAdapter extends BaseAdapter {

    private List<Elicopter> listaElicoptere=null;
    private Context ctx;
    private int resursaLayout;

    public ElicopterAdapter(List<Elicopter> listaElicoptere, Context ctx, int resursaLayout) {
        this.listaElicoptere = listaElicoptere;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return listaElicoptere.size();
    }

    @Override
    public Object getItem(int i) {
        return listaElicoptere.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,viewGroup,false);

        TextView numeProducatorTV=v.findViewById(R.id.numeProducatorTV);
        TextView pretTV=v.findViewById(R.id.pretTV);
        TextView autonomieTV=v.findViewById(R.id.autonomieTV);
        TextView numarLocuriTV=v.findViewById(R.id.numarLocuriTV);
        TextView tipTV=v.findViewById(R.id.tipTV);
        TextView data_fabricatieiDP=v.findViewById(R.id.data_fabricatieiTV);

        Elicopter elicopter= (Elicopter) getItem(i);



        numeProducatorTV.setText(elicopter.getProducator());
        pretTV.setText(String.valueOf(elicopter.getPret()));
        autonomieTV.setText(String.valueOf(elicopter.getAutonomie_Mile()));
        numarLocuriTV.setText(String.valueOf(elicopter.getNumarLocuri()));
        data_fabricatieiDP.setText(new StringBuilder()
                .append(elicopter.getDataFabricatiei().getYear())
                .append("/")
                .append(elicopter.getDataFabricatiei().getMonth()+1)
                .append("/").append(elicopter.getDataFabricatiei().getDate()).toString());
        tipTV.setText( elicopter.isNou() ? "Nou"  : "Utilzat");

        return v;
    }
}
