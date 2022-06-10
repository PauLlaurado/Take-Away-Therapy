package com.example.proba3.ui.notifications;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.ContratarCarrito;
import com.example.proba3.Infermeros;
import com.example.proba3.R;
import com.example.proba3.ui.dashboard.AdapterDash;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class AdapterNot extends RecyclerView.Adapter<AdapterNot.ViewHolderNot>{
private ArrayList<Infermeros>infermeroslist;

    public AdapterNot(ArrayList<Infermeros> infermeroslist) {
        this.infermeroslist = infermeroslist;
    }

    @Override
    public ViewHolderNot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderinfermers,null,false);
        return new AdapterNot.ViewHolderNot(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNot holder, int position) {
        holder.nameinfermero.setText(infermeroslist.get(position).getName());
        holder.descripcioninfermero.setText(infermeroslist.get(position).getDesc());
        holder.preciohora.setText(infermeroslist.get(position).getPrice()+" €/h");
        holder.imageView.setImageBitmap(infermeroslist.get(0).getBitmap());
    }

    @Override
    public int getItemCount() {
        return infermeroslist.size();
    }

    public class ViewHolderNot extends RecyclerView.ViewHolder{
        TextView nameinfermero,descripcioninfermero,preciohora;
        Button contratar;
        ImageView imageView;

        public ViewHolderNot(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageinfermer);
            nameinfermero=itemView.findViewById(R.id.nameinfermer);
            descripcioninfermero=itemView.findViewById(R.id.descinfermer);
            preciohora=itemView.findViewById(R.id.preciohora);
            contratar=itemView.findViewById(R.id.contratar);


            contratar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), ContratarCarrito.class);
                    String[] precio=preciohora.getText().toString().split(" ");

                    System.out.println(precio[0]);
                    intent.putExtra("nombre",nameinfermero.getText());
                    intent.putExtra("precio",precio[0]);
                    v.getContext().startActivity(intent);

                }
            });

        }
    }
}
