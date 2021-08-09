package com.example.aguacoop;

import android.os.Parcel;
import android.os.Parcelable;

public class TareasTrabajador implements Parcelable {

private int idTT,idMedidorTT,idUsuarioTT,idComunaTT,idDir2TT;
private String tareaAsignadaTT,fechaTT,nomUsuarioTT,nomComunaTT,direccionMedidorTT,latDirTT,lonDirTT;



    public TareasTrabajador(int idTT, int idMedidorTT, String tareaAsignadaTT, String nomUsuarioTT, String direccionMedidorTT, String latDirTT, String lonDirTT) {
        this.idTT = idTT;
        this.idMedidorTT = idMedidorTT;
        this.tareaAsignadaTT = tareaAsignadaTT;
        this.nomUsuarioTT = nomUsuarioTT;
        this.direccionMedidorTT = direccionMedidorTT;
        this.latDirTT = latDirTT;
        this.lonDirTT = lonDirTT;
    }

    public TareasTrabajador(int idTT, int idMedidorTT, int idUsuarioTT, String tareaAsignadaTT, String fechaTT, String nomUsuarioTT, String direccionMedidorTT, String latDirTT, String lonDirTT) {
        this.idTT = idTT;
        this.idMedidorTT = idMedidorTT;
        this.idUsuarioTT = idUsuarioTT;
        this.tareaAsignadaTT = tareaAsignadaTT;
        this.fechaTT = fechaTT;
        this.nomUsuarioTT = nomUsuarioTT;
        this.direccionMedidorTT = direccionMedidorTT;
        this.latDirTT = latDirTT;
        this.lonDirTT = lonDirTT;
    }

    public int getIdDir2TT() {
        return idDir2TT;
    }

    public void setIdDir2TT(int idDir2TT) {
        this.idDir2TT = idDir2TT;
    }

    public String getLatDirTT() {
        return latDirTT;
    }

    public void setLatDirTT(String latDirTT) {
        this.latDirTT = latDirTT;
    }

    public String getLonDirTT() {
        return lonDirTT;
    }

    public void setLonDirTT(String lonDirTT) {
        this.lonDirTT = lonDirTT;
    }



    public int getIdTT() {
        return idTT;
    }

    public void setIdTT(int idTT) {
        this.idTT = idTT;
    }

    public int getIdMedidorTT() {
        return idMedidorTT;
    }

    public void setIdMedidorTT(int idMedidorTT) {
        this.idMedidorTT = idMedidorTT;
    }

    public int getIdUsuarioTT() {
        return idUsuarioTT;
    }

    public void setIdUsuarioTT(int idUsuarioTT) {
        this.idUsuarioTT = idUsuarioTT;
    }

    public int getIdComunaTT() {
        return idComunaTT;
    }

    public void setIdComunaTT(int idComunaTT) {
        this.idComunaTT = idComunaTT;
    }

    public String getTareaAsignadaTT() {
        return tareaAsignadaTT;
    }

    public void setTareaAsignadaTT(String tareaAsignadaTT) {
        this.tareaAsignadaTT = tareaAsignadaTT;
    }

    public String getFechaTT() {
        return fechaTT;
    }

    public void setFechaTT(String fechaTT) {
        this.fechaTT = fechaTT;
    }

    public String getNomUsuarioTT() {
        return nomUsuarioTT;
    }

    public void setNomUsuarioTT(String nomUsuarioTT) {
        this.nomUsuarioTT = nomUsuarioTT;
    }

    public String getNomComunaTT() {
        return nomComunaTT;
    }

    public void setNomComunaTT(String nomComunaTT) {
        this.nomComunaTT = nomComunaTT;
    }

    public String getDireccionMedidorTT() {
        return direccionMedidorTT;
    }

    public void setDireccionMedidorTT(String direccionMedidorTT) {
        this.direccionMedidorTT = direccionMedidorTT;
    }

    @Override
    public String toString() {
        return "Direccion: "+direccionMedidorTT+"\nNro.Medidor: "+idMedidorTT;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idTT);
        dest.writeInt(this.idMedidorTT);
        dest.writeInt(this.idUsuarioTT);
        dest.writeInt(this.idComunaTT);
        dest.writeString(this.tareaAsignadaTT);
        dest.writeString(this.fechaTT);
        dest.writeString(this.nomUsuarioTT);
        dest.writeString(this.nomComunaTT);
        dest.writeString(this.direccionMedidorTT);
    }

    protected TareasTrabajador(Parcel in) {
        this.idTT = in.readInt();
        this.idMedidorTT = in.readInt();
        this.idUsuarioTT = in.readInt();
        this.idComunaTT = in.readInt();
        this.tareaAsignadaTT = in.readString();
        this.fechaTT = in.readString();
        this.nomUsuarioTT = in.readString();
        this.nomComunaTT = in.readString();
        this.direccionMedidorTT = in.readString();
    }

    public static final Creator<TareasTrabajador> CREATOR = new Creator<TareasTrabajador>() {
        @Override
        public TareasTrabajador createFromParcel(Parcel source) {
            return new TareasTrabajador(source);
        }

        @Override
        public TareasTrabajador[] newArray(int size) {
            return new TareasTrabajador[size];
        }
    };
}
