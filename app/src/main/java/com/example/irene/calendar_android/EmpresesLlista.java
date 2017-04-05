package com.example.irene.calendar_android;

/**
 * Created by Irene on 04/04/2017.
 */

public class EmpresesLlista {

    private String nomEmpresa;
    private String nomCarrer;

    public EmpresesLlista(String nomEmpresa, String nomCarrer) {
        this.nomEmpresa = nomEmpresa;
        this.nomCarrer = nomCarrer;
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


}
