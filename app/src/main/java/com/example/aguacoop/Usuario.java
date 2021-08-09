package com.example.aguacoop;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    String usuario, nombreUsuario,conUsuario,cargoUsuario;

    public Usuario() {
    }

    public Usuario(String usuario, String nombreUsuario, String conUsuario, String cargoUsuario) {
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
        this.conUsuario = conUsuario;
        this.cargoUsuario = cargoUsuario;
    }

    protected Usuario(Parcel in) {
        usuario = in.readString();
        nombreUsuario = in.readString();
        conUsuario = in.readString();
        cargoUsuario = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getConUsuario() {
        return conUsuario;
    }

    public void setConUsuario(String conUsuario) {
        this.conUsuario = conUsuario;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(nombreUsuario);
        dest.writeString(conUsuario);
        dest.writeString(cargoUsuario);
    }
}
