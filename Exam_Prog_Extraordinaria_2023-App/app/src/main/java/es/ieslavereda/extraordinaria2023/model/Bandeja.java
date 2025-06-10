package es.ieslavereda.extraordinaria2023.model;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import es.ieslavereda.extraordinaria2023.R;

public class Bandeja extends TableLayout {

    private Map<Integer, Bola> almacen;
    private Map<Integer, TextView> huecos;

    public Bandeja(Context context) {
        super(context);
        almacen = new HashMap<>();
        huecos = new HashMap<>();
    }

    public Bandeja(Context context, AttributeSet attrs) {
        super(context, attrs);
        almacen = new HashMap<>();
        huecos = new HashMap<>();
    }

    public void inicializa(){

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;

        TableRow tableRow;
        TextView textView;
        for(int fila = 0;fila<9;fila++){
            tableRow = new TableRow(getContext());
            for(int col=1;col<=10;col++){
                textView = new TextView(getContext());
                textView.setWidth(widh/10);
                textView.setHeight(widh/10);
                textView.setGravity(Gravity.CENTER_VERTICAL  | Gravity.CENTER_HORIZONTAL);
                textView.setText(""+(fila*10+col));
                textView.setTextColor(getResources().getColor(R.color.black,getContext().getTheme()));
                textView.setBackgroundColor(getContext().getResources().getColor(((fila+col)%2==0)?R.color.empty:R.color.empty2,getContext().getTheme()));
                huecos.put(fila*10+col,textView);
                tableRow.addView(textView);
            }
            addView(tableRow);
        }
    }

    public void addBola(Bola bola){
        almacen.put(bola.getNumero(),bola);
        TextView textView = huecos.get(bola.getNumero());
        if(textView!=null) {
            textView.setText(String.valueOf(bola.getNumero()));
            textView.setBackgroundColor(bola.getColor());
            textView.setTextColor(getResources().getColor(R.color.white,getContext().getTheme()));
        }
    }


}
