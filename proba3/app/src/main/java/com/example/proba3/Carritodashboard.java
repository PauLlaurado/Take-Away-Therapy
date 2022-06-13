package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proba3.adapter.AdapterCarrito;
import com.example.proba3.objetos.Medicamentos;

import java.util.ArrayList;

public class Carritodashboard extends AppCompatActivity {
    TextView preciototal;
    RecyclerView recyclerView;
    AdapterCarrito adapterCarrito;
    ArrayList<Medicamentos> medicamentoslist = new ArrayList<>();
    Button buttoncomprar;
    double preciototalint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito);

        preciototal = findViewById(R.id.preciototal);
        recyclerView = findViewById(R.id.carritolist);
        buttoncomprar = findViewById(R.id.buttoncomprar);

        recyclerView.setLayoutManager(new LinearLayoutManager(Carritodashboard.this));

        medicamentoslist = (ArrayList<Medicamentos>) getIntent().getSerializableExtra("milista");
        for (int i = 0; i < medicamentoslist.size(); i++) {
            double preciomedicamento = Double.parseDouble(medicamentoslist.get(i).getPrecio());
            preciototalint = preciomedicamento + preciototalint;

        }
        preciototal.setText(String.valueOf(preciototalint));
        adapterCarrito = new AdapterCarrito(medicamentoslist);
        recyclerView.setAdapter(adapterCarrito);


        buttoncomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carritodashboard.this, EfectuarPago.class);
                intent.putExtra("lista", medicamentoslist);
                intent.putExtra("destino", "comprar");
                intent.putExtra("precio", preciototalint);

                startActivity(intent);
            }
        });

    }

}
