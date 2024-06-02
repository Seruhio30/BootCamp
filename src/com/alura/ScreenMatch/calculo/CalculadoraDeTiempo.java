package com.alura.ScreenMatch.calculo;

import com.alura.ScreenMatch.modelos.Pelicula;
import com.alura.ScreenMatch.modelos.Serie;
import com.alura.ScreenMatch.modelos.Titulo;

public class CalculadoraDeTiempo {
    private int tiempoTotal;

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void incluye(Titulo titulo){
        this.tiempoTotal += titulo.getDuracionEnMinutos();
    }

}
