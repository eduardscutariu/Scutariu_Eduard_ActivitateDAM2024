package com.example.seminar4;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class TemperaturaAdapter extends BaseAdapter {
    private Context ctx;
    private int resursaLayout;
    private List<Pair<Double,Double>> temperaturi;

    public TemperaturaAdapter(Context ctx, int resursaLayout, List<Pair<Double, Double>> temperaturi) {
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
        this.temperaturi = temperaturi;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public int getResursaLayout() {
        return resursaLayout;
    }

    public void setResursaLayout(int resursaLayout) {
        this.resursaLayout = resursaLayout;
    }

    public List<Pair<Double, Double>> getTemperaturi() {
        return temperaturi;
    }

    public void setTemperaturi(List<Pair<Double, Double>> temperaturi) {
        this.temperaturi = temperaturi;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TemperaturaAdapter{");
        sb.append("ctx=").append(ctx);
        sb.append(", resursaLayout=").append(resursaLayout);
        sb.append(", temperaturi=").append(temperaturi);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int getCount() {
        return temperaturi.size();
    }

    @Override
    public Object getItem(int i) {
        return temperaturi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,viewGroup,false);

        TextView min=v.findViewById(R.id.textView3);
        TextView max=v.findViewById(R.id.textView7);

        Pair<Double,Double> pereche= (Pair<Double, Double>) getItem(i);

        min.setText(pereche.first.toString());
        max.setText(pereche.second.toString());

        return v;
    }
}
