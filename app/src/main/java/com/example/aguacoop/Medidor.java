package com.example.aguacoop;

public class Medidor {
    int idMedidor;
    String casaMedidor, comunaMedidor, contratoMedidor;

    public Medidor() {
    }

    public Medidor(int idMedidor, String casaMedidor, String comunaMedidor, String contratoMedidor) {
        this.idMedidor = idMedidor;
        this.casaMedidor = casaMedidor;
        this.comunaMedidor = comunaMedidor;
        this.contratoMedidor = contratoMedidor;
    }

    public int getIdMedidor() {
        return idMedidor;
    }

    public void setIdMedidor(int idMedidor) {
        this.idMedidor = idMedidor;
    }

    public String getCasaMedidor() {
        return casaMedidor;
    }

    public void setCasaMedidor(String casaMedidor) {
        this.casaMedidor = casaMedidor;
    }

    public String getComunaMedidor() {
        return comunaMedidor;
    }

    public void setComunaMedidor(String comunaMedidor) {
        this.comunaMedidor = comunaMedidor;
    }

    public String getContratoMedidor() {
        return contratoMedidor;
    }

    public void setContratoMedidor(String contratoMedidor) {
        this.contratoMedidor = contratoMedidor;
    }
}
