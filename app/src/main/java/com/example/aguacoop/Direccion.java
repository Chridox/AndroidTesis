package com.example.aguacoop;

public class Direccion {
    private int idDir;
    private String latitudLongitud;

    public Direccion(int idDir, String latitudLongitud) {
        this.idDir = idDir;
        this.latitudLongitud = latitudLongitud;
    }

    public int getIdDir() {
        return idDir;
    }

    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

    public String getLatitudLongitud() {
        return latitudLongitud;
    }

    public void setLatitudLongitud(String latitudLongitud) {
        this.latitudLongitud = latitudLongitud;
    }
}
