package com.example.proba3.ui.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.Carritodashboard;
import com.example.proba3.Medicamentos;
import com.example.proba3.R;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterDash extends RecyclerView.Adapter<AdapterDash.ViewHolderDash> {
    ArrayList<Medicamentos> medicamentos;
    ArrayList<Medicamentos> medicamentosArrayList = new ArrayList<>();


    public AdapterDash(ArrayList<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public ViewHolderDash onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderitems, null, false);
        return new ViewHolderDash(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDash holder, int position) {
        holder.nametv.setText(medicamentos.get(position).getNombre());
        holder.desctv.setText(medicamentos.get(position).getDecripcion());
        holder.canttv.setText(String.valueOf(medicamentos.get(position).getCantidad()));
        holder.preutv.setText(String.valueOf(medicamentos.get(position).getPrecio()));
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public class ViewHolderDash extends RecyclerView.ViewHolder {

        TextView nametv, desctv, canttv;
        Button preutv, agregar, rest, comprar;

        public ViewHolderDash(@NonNull View itemView) {
            super(itemView);
            nametv = itemView.findViewById(R.id.namemedciamento);
            desctv = itemView.findViewById(R.id.descmedicamento);
            canttv = itemView.findViewById(R.id.cantidad);
            preutv = itemView.findViewById(R.id.comprar);
            agregar = itemView.findViewById(R.id.agregar);
            rest = itemView.findViewById(R.id.restar);
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
nf.setMaximumFractionDigits(2);

            preutv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Medicamentos medicamentos = new Medicamentos();

                    int cantidad = Integer.parseInt(canttv.getText().toString());

                    medicamentos.setCantidad(String.valueOf(cantidad));
                    medicamentos.setNombre(nametv.getText().toString());

                    double precio = Double.parseDouble(preutv.getText().toString());

                    medicamentos.setPrecio(String.valueOf(precio));

                    medicamentosArrayList.add(medicamentos);
                    System.out.println(medicamentosArrayList.toString());
                    View view = itemView.getRootView();
                    comprar = view.findViewById(R.id.comprartodo);
                    comprar.setEnabled(true);
                    comprar.setVisibility(View.VISIBLE);

                    comprar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), Carritodashboard.class);
                            intent.putExtra("milista", medicamentosArrayList);
                            comprar.setEnabled(false);
                            comprar.setVisibility(View.INVISIBLE);
                            v.getContext().startActivity(intent);

                        }
                    });
                }
            });

            agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double precio = Double.parseDouble(preutv.getText().toString());

                    int cantidad = Integer.parseInt(canttv.getText().toString());
                    double preciounidad = precio / cantidad;

                    cantidad = cantidad + 1;

                    String cantstr = String.valueOf(cantidad);
                    canttv.setText(cantstr);
                    double preciofinal;
                    preciofinal = preciounidad * cantidad;
                    nf.setRoundingMode(RoundingMode.UP);

                    nf.format(preciofinal);

                    preutv.setText(String.valueOf(preciofinal));


                }
            });
            rest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double precio = Double.parseDouble(preutv.getText().toString());

                    int cantidad = Integer.parseInt(canttv.getText().toString());
                    double preciounidad = precio / cantidad;

                    cantidad = cantidad - 1;
                    if (cantidad == 0) {

                    } else {
                        String cantstr = String.valueOf(cantidad);
                        canttv.setText(cantstr);
                        double preciofinal;
                        preciofinal = preciounidad * cantidad;
                        nf.format(preciofinal);

                        preutv.setText(String.valueOf(preciofinal));
                    }

                }
            });

        }
    }
}
