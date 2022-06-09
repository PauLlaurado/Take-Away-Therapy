package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {

    private EditText emailpharmacy,passwordpharmacy,adresspharmacy,emailnurse,namenurse,passnurse,precio;
    private Button createpharmacy,createnurse;
    private FirebaseDatabase database1;
    private DatabaseReference ref1;
    Farmacia farmacia2=new Farmacia();
    Infermeros infermeros=new Infermeros();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        emailpharmacy=findViewById(R.id.emailpharmacy);
        passwordpharmacy=findViewById(R.id.passwordpharmacy);
        adresspharmacy=findViewById(R.id.adresspharmacy);
        createpharmacy=findViewById(R.id.buttoncreatefarmacia);
        emailnurse=findViewById(R.id.emailnurse);
        namenurse=findViewById(R.id.namenurse);
        passnurse=findViewById(R.id.passnurse);
        createnurse=findViewById(R.id.crearinfermero);
        precio=findViewById(R.id.pricenurse);


        createpharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                farmacia2.setEmail(emailpharmacy.getText().toString());
                farmacia2.setPassword(passwordpharmacy.getText().toString());
                farmacia2.setAdress(adresspharmacy.getText().toString());
                System.out.println(farmacia2.toString());
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                                emailpharmacy.getText().toString(),
                                passwordpharmacy.getText().toString()

                        ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        database1=FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/");
                        ref1=database1.getReference("Farmacia").push();

                        ref1.setValue(farmacia2);

                    } else {
                        Toast.makeText(getBaseContext(), "Pass required",
                                Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

        createnurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               infermeros.setEmail(emailnurse.getText().toString());
               infermeros.setName(namenurse.getText().toString());
               infermeros.setPassword(passnurse.getText().toString());
               infermeros.setPrice(precio.getText().toString());
               infermeros.setDesc("");

                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                                emailnurse.getText().toString(),
                                passnurse.getText().toString()

                        ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        database1=FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/");
                        ref1=database1.getReference("Infermeros").push();
                        ref1.setValue(infermeros);
                    } else {
                        Toast.makeText(getBaseContext(), "Pass required",
                                Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });


    }
    @Override public void onBackPressed() {
        Intent intent=new Intent(this, LogIn.class);
        startActivity(intent);
    }
}
