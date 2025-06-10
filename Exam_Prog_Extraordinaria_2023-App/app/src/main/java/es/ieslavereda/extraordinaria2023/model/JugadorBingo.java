package es.ieslavereda.extraordinaria2023.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JugadorBingo extends Jugador {

    private Set<Carton> cartonSet;

    public JugadorBingo(String nombre, String apellidos) {
        super(nombre, apellidos);
        cartonSet = new HashSet<>();
    }

    public void marcar(int numero) {
        Iterator<Carton> iterator = cartonSet.iterator();

        while (iterator.hasNext())
            iterator.next().marcar(numero);
    }

    public boolean isBingo() {
        boolean bingo = false;
        Iterator<Carton> iterator = cartonSet.iterator();

        while (iterator.hasNext() && !bingo)
            bingo = iterator.next().isBingo();

        return bingo;
    }

    public void addCarton(Carton c) {
        cartonSet.add(c);
    }


}
