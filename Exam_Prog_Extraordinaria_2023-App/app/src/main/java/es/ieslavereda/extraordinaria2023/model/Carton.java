package es.ieslavereda.extraordinaria2023.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import es.ieslavereda.extraordinaria2023.R;

public class Carton extends LinearLayout {

    private Map<Integer, Boolean> numeros;
    private Map<Integer, TextView> textViews;
    private final int CANT_NUMEROS = 10;


    public Carton(Context context) {
        super(context);
        numeros = new TreeMap<>();
        textViews = new HashMap<>();
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public Carton(Context context, AttributeSet attrs) {
        super(context, attrs);
        numeros = new TreeMap<>();
        textViews = new HashMap<>();
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void inicializa() {

        int numero;
        while (numeros.size() < CANT_NUMEROS)
            numeros.put((int) (Math.random() * 90 + 1), false);

        TextView textView;
        int i = 0;
        for (Integer n : numeros.keySet()) {
            textView = new TextView(getContext());
            textView.setText("" + n);
            textView.setTextColor(getResources().getColor(R.color.black, getContext().getTheme()));
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            textView.setBackgroundColor(getContext().getResources().getColor((i++ % 2 == 0) ? R.color.empty : R.color.empty2, getContext().getTheme()));
            textView.setTextSize(10);
            textView.setWidth(36);
            addView(textView);
            textViews.put(n, textView);
        }
    }

    public void marcar(int numero) {
        if (numeros.keySet().contains(numero)) {
            numeros.put(numero, true);
            textViews.get(numero).setBackgroundColor(getResources().getColor(R.color.marcado, getContext().getTheme()));
        }
    }

    public boolean isBingo() {
        return !numeros.values().contains(false);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Carton))
            return false;

        return ((Carton) obj).numeros.keySet().containsAll(numeros.keySet());
    }
}
