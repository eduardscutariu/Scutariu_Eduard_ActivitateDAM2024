package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.zip.Inflater;

public class ImageAdapter extends BaseAdapter {
    private List<Imagine> listaImagini;
    private Context ctx;
    private int resursaLayout;

    public List<Imagine> getListaImagini() {
        return listaImagini;
    }

    public void setListaImagini(List<Imagine> listaImagini) {
        this.listaImagini = listaImagini;
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

    public ImageAdapter(List<Imagine> listaImagini, Context ctx, int resursaLayout) {
        this.listaImagini = listaImagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return listaImagini.size();
    }

    @Override
    public Object getItem(int i) {
        return listaImagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,viewGroup,false);

        ImageView img=v.findViewById(R.id.imageElicopter);

        Imagine imagine= (Imagine) getItem(i);

        img.setImageBitmap(imagine.getImagine());

        return v;
    }
}
