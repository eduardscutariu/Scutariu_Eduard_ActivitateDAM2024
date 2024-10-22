package com.example.seminar4;

import androidx.annotation.NonNull;

import java.util.Date;

public class Elicopter {
    private String producator;
    private float pret;
    private float autonomie_Mile;
    private int numarLocuri;
    private Date dataFabricatiei;

    private String culoare;

    private boolean nou;
//

    public Elicopter(String producator, float pret, float autonomie_Mile, int numarLocuri, Date dataFabricatiei, String culoare, boolean nou) {
        this.producator = producator;
        this.pret = pret;
        this.autonomie_Mile = autonomie_Mile;
        this.numarLocuri = numarLocuri;
        this.dataFabricatiei = dataFabricatiei;
        this.culoare = culoare;
        this.nou = nou;
    }

    public Elicopter() {
        this.culoare = "negru";
        this.nou = false;
        this.producator = "necunoscut";
        this.pret = 0.0f;
        this.autonomie_Mile = 0.0f;
        this.numarLocuri = 1;
        this.dataFabricatiei = new Date();
    }



    public String getProducator() {
        return producator;
    }

    public float getPret() {
        return pret;
    }

    public float getAutonomie_Mile() {
        return autonomie_Mile;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public Date getDataFabricatiei() {
        return dataFabricatiei;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Elicopter{");
        sb.append("producator='").append(producator).append('\'');
        sb.append(", pret=").append(pret);
        sb.append(", autonomie_Mile=").append(autonomie_Mile);
        sb.append(", numarLocuri=").append(numarLocuri);
        sb.append(", dataFabricatiei=").append(dataFabricatiei);
        sb.append('}');
        return sb.toString();
    }
}

