package com.example.proba3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.R;
import com.example.proba3.objetos.Pedido;

public class Adapteritempedido extends RecyclerView.Adapter<Adapteritempedido.Viewholderitempedido> {

    Pedido pedido;

    public Adapteritempedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @NonNull
    @Override
    public Viewholderitempedido onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderitempedido, null, false);
        return new Adapteritempedido.Viewholderitempedido(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholderitempedido holder, int position) {
        if (pedido.getContractat().equals("contractat")) {
            holder.textView.setText(pedido.getInfermer());
        } else {
            holder.textView.setText(pedido.getArrayListmedicamentos().get(position).getNombre() + "\t  " + pedido.getArrayListmedicamentos().get(position).getCantidad());
        }

    }

    @Override
    public int getItemCount() {
        if (pedido.getContractat().equals("contractat")) {

            return 1;
        } else {
            return pedido.getArrayListmedicamentos().size();
        }
    }

    public class Viewholderitempedido extends RecyclerView.ViewHolder {
        TextView textView;

        public Viewholderitempedido(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textviewitempedido);
        }
    }
}


