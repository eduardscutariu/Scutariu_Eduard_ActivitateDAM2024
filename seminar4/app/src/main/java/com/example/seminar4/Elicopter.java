package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Elicopter implements Parcelable
{
    private String producator;
    private float pret;
    private float autonomie_Mile;
    private int numarLocuri;
    private Date dataFabricatiei;
    private boolean nou;
//

    public Elicopter(String producator, float pret, float autonomie_Mile, int numarLocuri, Date dataFabricatiei, boolean nou) {
        this.producator = producator;
        this.pret = pret;
        this.autonomie_Mile = autonomie_Mile;
        this.numarLocuri = numarLocuri;
        this.dataFabricatiei = dataFabricatiei;
        this.nou = nou;
    }

    public Elicopter() {
        this.nou = false;
        this.producator = "necunoscut";
        this.pret = 0.0f;
        this.autonomie_Mile = 0.0f;
        this.numarLocuri = 1;
        this.dataFabricatiei = new Date();
    }


    protected Elicopter(Parcel in) {
        producator = in.readString();
        pret = in.readFloat();
        autonomie_Mile = in.readFloat();
        numarLocuri = in.readInt();
        nou = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(producator);
        dest.writeFloat(pret);
        dest.writeFloat(autonomie_Mile);
        dest.writeInt(numarLocuri);
        dest.writeByte((byte) (nou ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Elicopter> CREATOR = new Creator<Elicopter>() {
        @Override
        public Elicopter createFromParcel(Parcel in) {
            return new Elicopter(in);
        }

        @Override
        public Elicopter[] newArray(int size) {
            return new Elicopter[size];
        }
    };

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

