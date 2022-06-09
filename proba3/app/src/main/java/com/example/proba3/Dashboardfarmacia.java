package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.ui.dashboard.AdapterDash;

import java.util.ArrayList;


public class Dashboardfarmacia extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapterpedido adapterpedido;
    Pedido pedido,pedido2;
    ArrayList<Pedido>pedidoslist=new ArrayList<>();
    ArrayList<Medicamentos>medicamentosvacio=new ArrayList<>();
    ArrayList<Medicamentos>medicamentoslist=new ArrayList<>();
    Medicamentos medicamentos,medicamentos2;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmacia);


        recyclerView = findViewById(R.id.recyclerviewpedido);
        linearLayout=findViewById(R.id.llpedido);

        pedido=new Pedido();
        pedido.setPagado("pagado");
        pedido.setContractat("contractat");
        pedido.setId("0");
        pedido.setInfermer("Contractat Pau\nServei: Mesura de constants vitals");
        pedido.setAddress("asdasd");
        pedido.setArrayListmedicamentos(medicamentosvacio);

        medicamentos=new Medicamentos();
        medicamentos.setCantidad("10");
        medicamentos.setNombre("ibuprofeno");
        medicamentos.setPrecio("10");
        medicamentos.setDecripcion("antigripal");

        medicamentos2=new Medicamentos();
        medicamentos2.setCantidad("10");
        medicamentos2.setNombre("paracetamol");
        medicamentos2.setPrecio("10");
        medicamentos2.setDecripcion("antigripal");
        medicamentoslist.add(medicamentos);
        medicamentoslist.add(medicamentos2);

        pedido2=new Pedido();
        pedido2.setPagado("Efectivo");
        pedido2.setId("1");
        pedido2.setContractat("Comprat");
        pedido2.setInfermer("");
        pedido2.setAddress("qweqew");
        pedido2.setArrayListmedicamentos(medicamentoslist);

        pedidoslist.add(pedido);
        pedidoslist.add(pedido2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterpedido=new Adapterpedido(pedidoslist);
        recyclerView.setAdapter(adapterpedido);



    }

    public void deletefirst(){
        Toast.makeText(Dashboardfarmacia.this, "toast", Toast.LENGTH_SHORT).show();
        pedidoslist.remove(0);
        System.out.println("holaquetla");
    }

    @Override public void onBackPressed() {
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }

    }
