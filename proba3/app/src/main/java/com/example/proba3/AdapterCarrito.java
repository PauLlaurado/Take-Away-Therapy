package com.example.proba3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.ViewHolderCarrito>{

    ArrayList<Medicamentos> medicamentos;


    public AdapterCarrito(ArrayList<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public ViewHolderCarrito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholdercarrito,null,false);
        return new AdapterCarrito.ViewHolderCarrito(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCarrito holder, int position) {
holder.nametv.setText(medicamentos.get(position).getNombre());
holder.canttv.setText(String.valueOf( medicamentos.get(position).getCantidad()));
holder.preutv.setText(String.valueOf( medicamentos.get(position).getPrecio()));
    }

    @Override
    public int getItemCount() {
return medicamentos.size();
    }

    public class ViewHolderCarrito extends RecyclerView.ViewHolder {
        public TextView nametv,canttv,preutv;
        public ViewHolderCarrito(@NonNull View itemView) {
            super(itemView);
            nametv=itemView.findViewById(R.id.carritoname);
            canttv=itemView.findViewById(R.id.carrtiocant);
            preutv=itemView.findViewById(R.id.carritopreu);
        }
    }
}
