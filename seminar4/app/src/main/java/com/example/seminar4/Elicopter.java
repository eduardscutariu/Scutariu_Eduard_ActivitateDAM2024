package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
@Entity(tableName = "TabelElicoptere")
public class Elicopter implements Parcelable
{
    @PrimaryKey
    @NonNull
    private String producator;
    private float pret;
    private float autonomie_Mile;
    private int numarLocuri;
    @TypeConverters(ConversieDateToLong.class)
    private Date dataFabricatiei;
    private boolean nou;

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

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setAutonomie_Mile(float autonomie_Mile) {
        this.autonomie_Mile = autonomie_Mile;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }

    protected Elicopter(Parcel in) {
        producator = in.readString();
        pret = in.readFloat();
        autonomie_Mile = in.readFloat();
        numarLocuri = in.readInt();
        dataFabricatiei=(java.util.Date)in.readSerializable();
        nou = in.readByte() != 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(producator);
        dest.writeFloat(pret);
        dest.writeFloat(autonomie_Mile);
        dest.writeInt(numarLocuri);
        dest.writeSerializable(dataFabricatiei);
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

    public void setProducator(@NonNull String producator) {
        this.producator = producator;
    }

    public float getPret() {
        return pret;
    }

    public void setDataFabricatiei(Date dataFabricatiei) {
        this.dataFabricatiei = dataFabricatiei;
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

    public boolean isNou() {
        return nou;
    }

    public void setNou(boolean nou) {
        this.nou = nou;
    }
    public void setTip(String tip)
    {
        if (tip.equals("nou"))
            this.nou=true;
        else
            this.nou=false;

    }

    public String getTip()
    {
       if (nou)
        return "nou";
       else
        return "utilizat";

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Elicopter{");
        sb.append("producator='").append(producator).append('\'');
        sb.append(", pret=").append(pret);
        sb.append(", autonomie_Mile=").append(autonomie_Mile);
        sb.append(", numarLocuri=").append(numarLocuri);
        sb.append(", dataFabricatiei=").append(dataFabricatiei);
        sb.append(", nou=").append(nou);
        sb.append('}');
        return sb.toString();
    }

    public String getKey()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.producator);
        sb.append(this.pret);
        sb.append(this.dataFabricatiei);

        return  sb.toString();
    }

}

