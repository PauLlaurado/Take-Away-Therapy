package com.example.proba3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proba3.objetos.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    private EditText editTextemail, editTextpass, editTextaddres, editTextfloor, editTextdoor, editTextphone;
    private Button update;

    private DatabaseReference mDatabase;
    private ArrayList<Users> userslist = new ArrayList<>();
    private ArrayList<String> idlist = new ArrayList<>();
    int a = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        editTextemail = findViewById(R.id.emailupdate);
        editTextpass = findViewById(R.id.passwordupdate);
        editTextaddres = findViewById(R.id.addresupdate);
        editTextfloor = findViewById(R.id.floorupdate);
        editTextdoor = findViewById(R.id.doorupdate);
        editTextphone = findViewById(R.id.phoneupdate);
        update = findViewById(R.id.update);

        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Users user = new Users();
                    user = singleSnapshot.getValue(Users.class);
                    idlist.add(singleSnapshot.getKey());
                    userslist.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (update.getText().toString().equals("Actualizar")) {

                    editTextemail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    for (int i = 0; i < userslist.size(); i++) {
                        if (userslist.get(i).getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                            a = i;
                            editTextpass.setText(userslist.get(i).getPassword());
                            editTextaddres.setText(userslist.get(i).getAdress());
                            editTextfloor.setText(userslist.get(i).getFloor());
                            editTextdoor.setText(userslist.get(i).getDoor());
                            editTextphone.setText(userslist.get(i).getPhone());
                        }
                    }

                    editTextemail.setEnabled(true);
                    editTextpass.setEnabled(true);
                    editTextaddres.setEnabled(true);
                    editTextfloor.setEnabled(true);
                    editTextdoor.setEnabled(true);
                    editTextphone.setEnabled(true);


                    update.setText("Guardar");
                } else if (update.getText().toString().equals("Guardar")) {
                    update.setText("Actualizar");

                    mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
                    Users users = new Users();
                    users.setName(userslist.get(a).getName());
                    users.setEmail(editTextemail.getText().toString());
                    users.setPassword(editTextpass.getText().toString());
                    users.setAdress(editTextaddres.getText().toString());
                    users.setFloor(editTextfloor.getText().toString());
                    users.setDoor(editTextdoor.getText().toString());
                    users.setPhone(editTextphone.getText().toString());

                    mDatabase.child(idlist.get(a)).setValue(users);
                    FirebaseAuth.getInstance().getCurrentUser().updateEmail(editTextemail.getText().toString());
                    FirebaseAuth.getInstance().getCurrentUser().updatePassword(editTextpass.getText().toString());

                    editTextemail.setEnabled(false);
                    editTextpass.setEnabled(false);
                    editTextaddres.setEnabled(false);
                    editTextfloor.setEnabled(false);
                    editTextdoor.setEnabled(false);
                    editTextphone.setEnabled(false);

                    Toast.makeText(Perfil.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();


                }


            }
        });

    }
}
