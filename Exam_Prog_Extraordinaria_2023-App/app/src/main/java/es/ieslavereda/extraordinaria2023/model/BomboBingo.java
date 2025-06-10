package es.ieslavereda.extraordinaria2023.model;

import android.content.Context;

import es.ieslavereda.extraordinaria2023.R;


public class BomboBingo extends Bombo<Bola>{


    public BomboBingo(Context context) {
        Bola b;
        int color;
        for(int i=1;i<=90;i++){
            color= context.getResources().getColor((Math.random()<0.5)?R.color.green:R.color.red, context.getTheme());
            add(new Bola(i,color));
        }
        shuffle();
    }

    @Override
    public Bola get() {
        if(bolas.size()%5==0)
            shuffle();
        return super.get();
    }

}
