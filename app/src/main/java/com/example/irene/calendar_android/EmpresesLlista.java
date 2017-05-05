package com.example.irene.calendar_android;

/**
 * Created by Irene on 04/04/2017.
 */

public class EmpresesLlista {

    private String nomEmpresa;
    private String nomCarrer;
    private String imatge;

    public EmpresesLlista(String nomEmpresa, String nomCarrer, String imatge) {
        this.nomEmpresa = nomEmpresa;
        this.nomCarrer = nomCarrer;
        this.imatge = imatge;
    }

    public String getNomCarrer() {
        return nomCarrer;
    }

    public void setNomCarrer(String nomCarrer) {
        this.nomCarrer = nomCarrer;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }
}
