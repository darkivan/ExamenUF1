package com.example.examenuf1;

public class InfoTaller {

    String valoracion;
    String comentario;

    public  InfoTaller(){}

    public InfoTaller(String valoracion, String comentario) {
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
