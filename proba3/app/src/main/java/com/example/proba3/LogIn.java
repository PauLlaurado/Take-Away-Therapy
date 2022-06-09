package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    public EditText editTextemail,editTextpassword;
    private Button login;
    private TextView textViewregister,contact;
    private ArrayList<Users>userslist=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference ref;
    private ArrayList<Farmacia> farmaciaslist=new ArrayList<>();
    private ArrayList<Infermeros>infermeroslist=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);




                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }





        contact=findViewById(R.id.contactus);
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        textViewregister = findViewById(R.id.goToRegister);
        login = findViewById(R.id.emailSignIn);

        database = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/");

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,ContactUs.class);
                startActivity(intent);
            }
        });

        ref=database.getReference("Infermeros");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Infermeros infermeros=new Infermeros();
                    infermeros=singleSnapshot.getValue(Infermeros.class);
                    infermeroslist.add(infermeros);
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref=database.getReference("Farmacia");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Farmacia farmacia=new Farmacia();
                    farmacia=singleSnapshot.getValue(Farmacia.class);
                    farmaciaslist.add(farmacia);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        ref = database.getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String id = ds.getKey();
                        Users user = new Users();
                        user.setEmail(ds.child("email").getValue().toString());
                        user.setPassword(ds.child("password").getValue().toString());
                        user.setName(ds.child("name").getValue().toString());
                        userslist.add(user);

                    }
//                    Toast.makeText(LogIn.this, "datachanged", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean notuser=false;
                for (int i = 0; i <infermeroslist.size() ; i++) {
                    if (infermeroslist.get(i).getEmail().equals(editTextemail.getText().toString())&&infermeroslist.get(i).getPassword().equals(editTextpassword.getText().toString())){
                        Intent intent=new Intent(LogIn.this,Dashboardfarmacia.class);
                        startActivity(intent);
                        notuser=true;
                        finish();
                    }
                }
                for (int i = 0; i <farmaciaslist.size() ; i++) {

                    if(farmaciaslist.get(i).getEmail().equals(editTextemail.getText().toString())&&farmaciaslist.get(i).getPassword().equals(editTextpassword.getText().toString())){
                        Intent intent=new Intent(LogIn.this,Dashboardinfermero.class);
                        intent.putExtra("email",editTextemail.getText().toString());
                        startActivity(intent);
                        notuser=true;
                        finish();
                    }
                }
                if (!notuser){

                    if (editTextemail.getText().toString().equals("admin") && editTextpassword.getText().toString().equals("1234")) {
                        Intent intent = new Intent(LogIn.this, Admin.class);
                        startActivity(intent);
                        finish();
                    } else {
                        FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(
                                        editTextemail.getText().toString(),
                                        editTextpassword.getText().toString()
                                ).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Incorrect  email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                    }
                }
            }

        });


        textViewregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override public void onBackPressed() {

    }
}
