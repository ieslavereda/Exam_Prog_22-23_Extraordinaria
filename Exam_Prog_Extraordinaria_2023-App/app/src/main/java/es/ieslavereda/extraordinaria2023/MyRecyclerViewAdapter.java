package es.ieslavereda.extraordinaria2023;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.ieslavereda.extraordinaria2023.model.Bola;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<Bola> values;
    private List<Bola> aux;
    private LayoutInflater inflater;
    private Context context;

    private Comparator<Bola> comparator;

    public MyRecyclerViewAdapter(@NonNull Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        values = new ArrayList<>();
        aux = new ArrayList<>();
    }
    public void insertValue(Bola value){
        values.add(value);
        aux.add(value);
        notifyItemInserted(values.size());
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        Bola b = aux.get(position);
        holder.rId.setText(String.valueOf(position+1));
        holder.rBola.setText(""+b.getNumero());
        holder.rBola.setBackgroundColor(b.getColor());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public void sort(Comparator<Bola> c){
        this.comparator = c;
        aux.sort(c);
        notifyDataSetChanged();
    }
    public void sort(){
        Collections.sort(aux);
        notifyDataSetChanged();
    }
    public void sortTime(){
        aux.clear();
        aux.addAll(values);
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rId;
        TextView rBola;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rId = itemView.findViewById(R.id.rId);
            rBola = itemView.findViewById(R.id.rbola);
        }

    }
}
