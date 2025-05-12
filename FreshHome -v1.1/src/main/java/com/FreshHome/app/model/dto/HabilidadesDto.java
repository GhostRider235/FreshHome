package com.FreshHome.app.model.dto;



public class HabilidadesDto {
    private String Habilidad;
    private int añosExperiencia;
    private int idUsuario;


    public HabilidadesDto(String Habilidad, int añosExperiencia, int idUsuario) {
        this.Habilidad = Habilidad;
        this.añosExperiencia = añosExperiencia;
        this.idUsuario = idUsuario;
    }



    public HabilidadesDto() {
    }

    public String getHabilidad() {
        return this.Habilidad;
    }

    public void setHabilidad(String Habilidad) {
        this.Habilidad = Habilidad;
    }

    public int getAñosExperiencia() {
        return this.añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}