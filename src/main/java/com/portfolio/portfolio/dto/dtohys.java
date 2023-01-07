package com.portfolio.portfolio.dto;

import javax.validation.constraints.NotBlank;

public class dtohys {
   @NotBlank
   private String nombre;
   @NotBlank
   private int porcentaje; 
   @NotBlank
   private String color;

    public dtohys() {
    }

    public dtohys(String nombre, int porcentaje, String color) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
   
   
}
