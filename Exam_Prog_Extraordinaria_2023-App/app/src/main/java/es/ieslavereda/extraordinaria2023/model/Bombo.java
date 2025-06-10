package es.ieslavereda.extraordinaria2023.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Bombo<T> {

    protected Set<T> bolas;

    public Bombo(){
        bolas = new LinkedHashSet<>();
    }

    public void shuffle(){
        List<T> aux = new ArrayList<>(bolas);
        Collections.shuffle(aux);
        bolas = new LinkedHashSet<>(aux);
    }

    public T get(){
        T data = null;
        Iterator<T> iterator = bolas.iterator();
        if(iterator.hasNext()){
            data = iterator.next();
            iterator.remove();
        }
        return data;
    }

    public void add(T data){
        bolas.add(data);
    }

}
