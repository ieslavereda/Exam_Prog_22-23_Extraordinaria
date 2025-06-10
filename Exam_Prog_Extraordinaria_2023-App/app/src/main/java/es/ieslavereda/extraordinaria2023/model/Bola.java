package es.ieslavereda.extraordinaria2023.model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Bola implements Comparable<Bola>{
    private int numero;
    private int color;


    public Bola( int numero, int color) {
        this.numero = numero;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return numero;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Bola))
            return false;

        return ((Bola) obj).numero == numero;
    }

    @Override
    public int compareTo(Bola bola) {
        if(bola.getColor()!=getColor())
            return bola.getColor()-getColor();
        return getNumero()-bola.getNumero();
    }
}
