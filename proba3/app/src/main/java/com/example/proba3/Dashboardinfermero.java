package com.example.proba3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.adapter.Adapterpedido;
import com.example.proba3.objetos.Infermeros;
import com.example.proba3.objetos.Pedido;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboardinfermero extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapterpedido adapterpedido;
    private String myText;
    private DatabaseReference mDatabase;
    private ArrayList<Infermeros> infermeroslist = new ArrayList<>();
    private ArrayList<String> stringid = new ArrayList<>();
    String a = "";
    Button button;
    ArrayList<Pedido> pedidofinal = new ArrayList<>();
    ArrayList<Pedido> pedidoslist = new ArrayList<>();
    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardinfermero);
        button = findViewById(R.id.mapa);
        recyclerView=findViewById(R.id.recyclerpedidosinfermer);
        pedido = new Pedido();
        pedido.setPagado("pagado");
        pedido.setContractat("contractat");
        pedido.setId("0");
        pedido.setInfermer("Contractat Pau\nServei: Mesura de constants vitals");
        pedido.setAddress("Baluart 80 5 1");

        pedidoslist.add(pedido);


        for (int i = 0; i < pedidoslist.size(); i++) {
            if (pedidoslist.get(i).getContractat().equals("contractat")) {
                pedidofinal.add(pedidoslist.get(i));
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboardinfermero.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Infermeros");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Infermeros infermeros = new Infermeros();
                    infermeros = singleSnapshot.getValue(Infermeros.class);
                    infermeroslist.add(infermeros);
                    stringid.add(singleSnapshot.getKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterpedido = new Adapterpedido(pedidofinal);
        recyclerView.setAdapter(adapterpedido);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        pedidofinal.remove(position);
                        recyclerView.removeViewAt(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }


    void alertDialogDemo() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Escribe una breve descripcion tuya").setTitle("Descripcion")
                .setCancelable(false);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Infermeros infermeroupdate = new Infermeros();
                myText = editText.getText().toString();

//                String intentstr=getIntent().getStringExtra("email");
                String intentstr = "infermero@gmail.com";
                for (int i = 0; i < infermeroslist.size(); i++) {
                    if (infermeroslist.get(i).getEmail().equals(intentstr)) {
                        a = stringid.get(i);
                        infermeroupdate.setPrice(infermeroslist.get(i).getPrice());
                        infermeroupdate.setName(infermeroslist.get(i).getName());
                        infermeroupdate.setPassword(infermeroslist.get(i).getPassword());
                        infermeroupdate.setDesc(myText);
                        infermeroupdate.setEmail(infermeroslist.get(i).getEmail());
                    }

                }
                mDatabase.child(a).setValue(infermeroupdate);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        for (int i = 0; i < infermeroslist.size(); i++) {
            if (infermeroslist.get(i).getDesc().isEmpty()) {
                alertDialogDemo();
            } else {
                Toast.makeText(this, "Bienvenido" + " " + infermeroslist.get(i).getName(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}
