package com.example.proba3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.ui.notifications.AdapterNot;

import java.util.ArrayList;

public class Adapterpedido extends RecyclerView.Adapter<Adapterpedido.Viewholderpedido>  {

    ArrayList<Pedido>pedidos;
    RecyclerView.RecycledViewPool recycledViewPool=new RecyclerView.RecycledViewPool();

    public Adapterpedido(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public Viewholderpedido onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderpedido,null,false);
        return new Adapterpedido.Viewholderpedido(view);       }

    @Override
    public void onBindViewHolder(@NonNull Viewholderpedido holder, int position) {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(holder.recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);

        holder.pagado.setText(pedidos.get(position).getPagado());

        holder.address.setText(pedidos.get(position).getAddress());
            linearLayoutManager.setInitialPrefetchItemCount(1);

            Adapteritempedido adapteritempedido=new Adapteritempedido(pedidos.get(position));

            holder.recyclerView.setLayoutManager(linearLayoutManager);
            holder.recyclerView.setAdapter(adapteritempedido);
            holder.recyclerView.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public class Viewholderpedido extends RecyclerView.ViewHolder {

        TextView comanda,pagado,address;
        RecyclerView recyclerView;
        public Viewholderpedido(@NonNull View itemView) {
            super(itemView);

            comanda=itemView.findViewById(R.id.comanda);
            pagado=itemView.findViewById(R.id.pagado);
            recyclerView=itemView.findViewById(R.id.recyclerviewitempedido);
            address=itemView.findViewById(R.id.textViewaddrespedido);

        }
    }
}
