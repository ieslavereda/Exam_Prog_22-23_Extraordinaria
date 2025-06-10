package es.ieslavereda.extraordinaria2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.ieslavereda.extraordinaria2023.api.Connector;
import es.ieslavereda.extraordinaria2023.base.BaseActivity;
import es.ieslavereda.extraordinaria2023.base.CallInterface;
import es.ieslavereda.extraordinaria2023.model.Bandeja;
import es.ieslavereda.extraordinaria2023.model.Bola;
import es.ieslavereda.extraordinaria2023.model.BomboBingo;
import es.ieslavereda.extraordinaria2023.model.Carton;
import es.ieslavereda.extraordinaria2023.model.JugadorBingo;

public class MainActivity extends BaseActivity {

    private Bandeja bandeja;
    private TextView display;
    private BomboBingo bombo;
    private RecyclerView recycler;

    private RadioButton sortNumber;

    private RadioButton sortTime;
    private RadioButton sortNatural;

    private List<JugadorBingo> jugadors;

    private MyRecyclerViewAdapter adapter;
    private Carton carton1;
    private Carton carton2;
    private Carton carton3;
    private Carton carton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setLayout(R.layout.activity_main);

        display = findViewById(R.id.bola);
        bandeja = findViewById(R.id.bandeja);
        recycler = findViewById(R.id.recycler);
        sortNumber = findViewById(R.id.radioButtonSortNumber);
        sortTime = findViewById(R.id.radioButtonSortTime);
        sortNatural=findViewById(R.id.radioButtonSortNatural);
        carton1 = findViewById(R.id.carton1);
        carton2 = findViewById(R.id.carton2);
        carton3 = findViewById(R.id.carton3);
        carton4 = findViewById(R.id.carton4);

        bandeja.inicializa();

        adapter = new MyRecyclerViewAdapter(getApplicationContext());
        recycler.setAdapter(adapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayout);

        bombo = new BomboBingo(getApplicationContext());

        display.setOnClickListener(view -> {
            sacarBola();
        });

        sortNumber.setOnClickListener(view -> adapter.sort(Comparator.comparingInt(Bola::getNumero)));
        sortTime.setOnClickListener(view -> adapter.sortTime());
        sortNatural.setOnClickListener(view -> adapter.sort());

        jugadors = new ArrayList<>();
        jugadors.add(new JugadorBingo("Joaquin","Alonso"));
        jugadors.add(new JugadorBingo("Xavi","Rosillo"));
        jugadors.add(new JugadorBingo("Jose Miguel","Fajardo"));
        jugadors.add(new JugadorBingo("Javi","Garcia"));

        carton1.inicializa();
        carton2.inicializa();
        carton3.inicializa();
        carton4.inicializa();

        jugadors.get(0).addCarton(carton1);
        jugadors.get(1).addCarton(carton2);
        jugadors.get(2).addCarton(carton3);
        jugadors.get(3).addCarton(carton4);

        showProgress();
        executeCall(new CallInterface() {
            @Override
            public void doInBackground() {
                Connector.getConector().delete(String.class,"bingo");
            }

            @Override
            public void doInUI() {
                hideProgress();
                bandeja.setVisibility(View.GONE);
            }
        });

    }

    private void sacarBola() {
        List<JugadorBingo> ganadores = new ArrayList<>();
        Bola bola = bombo.get();
        display.setText(String.valueOf(bola.getNumero()));
        display.setBackgroundColor(bola.getColor());
        bandeja.addBola(bola);
        adapter.insertValue(bola);

        for(JugadorBingo j : jugadors){
            j.marcar(bola.getNumero());
            if(j.isBingo())
                ganadores.add(j);
        }

        if(ganadores.size()>0){
            display.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Los ganadore son : " + ganadores, Toast.LENGTH_LONG ).show();

        }

        showProgress();
        executeCall(new CallInterface() {
            @Override
            public void doInBackground() {
                Connector.getConector().post(Bola.class,bola,"bingo");
            }

            @Override
            public void doInUI() {
                hideProgress();
                bandeja.setVisibility(View.GONE);
            }
        });



    }
}