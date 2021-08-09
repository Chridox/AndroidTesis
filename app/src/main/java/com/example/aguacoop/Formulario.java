package com.example.aguacoop;

public class Formulario {
    int idFormulaio,idMedidor;
    String trabajoFormulario,observacionesFormulario,firmaFormulario,usuario;

    public Formulario() {
    }

    public Formulario(int idFormulaio, int idMedidor, String trabajoFormulario, String observacionesFormulario, String firmaFormulario, String usuario) {
        this.idFormulaio = idFormulaio;
        this.idMedidor = idMedidor;
        this.trabajoFormulario = trabajoFormulario;
        this.observacionesFormulario = observacionesFormulario;
        this.firmaFormulario = firmaFormulario;
        this.usuario = usuario;
    }

    public int getIdFormulaio() {
        return idFormulaio;
    }

    public void setIdFormulaio(int idFormulaio) {
        this.idFormulaio = idFormulaio;
    }

    public int getIdMedidor() {
        return idMedidor;
    }

    public void setIdMedidor(int idMedidor) {
        this.idMedidor = idMedidor;
    }

    public String getTrabajoFormulario() {
        return trabajoFormulario;
    }

    public void setTrabajoFormulario(String trabajoFormulario) {
        this.trabajoFormulario = trabajoFormulario;
    }

    public String getObservacionesFormulario() {
        return observacionesFormulario;
    }

    public void setObservacionesFormulario(String observacionesFormulario) {
        this.observacionesFormulario = observacionesFormulario;
    }

    public String getFirmaFormulario() {
        return firmaFormulario;
    }

    public void setFirmaFormulario(String firmaFormulario) {
        this.firmaFormulario = firmaFormulario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
