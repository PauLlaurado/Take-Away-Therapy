package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    public EditText editTextname,editTextemail,editTextphone,editTextadress,editTextfloor,editTextdoor,editTextpass;
    public Button buttonverfyemail,buttonregister;
    public String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    private FirebaseDatabase database;
    private DatabaseReference ref;
    Users user=new Users();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editTextname=findViewById(R.id.nameEditText);
        editTextemail=findViewById(R.id.emailEditText2);
        editTextphone=findViewById(R.id.phoneEditText3);
        editTextadress=findViewById(R.id.adressEditText4);
        editTextfloor=findViewById(R.id.floorEditText5);
        editTextdoor=findViewById(R.id.doorEditText6);
        editTextpass=findViewById(R.id.passwordEditText);
        buttonregister=findViewById(R.id.createAccountButton);




        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextpass.getText().toString().isEmpty()) {
                    editTextpass.setError("Required");
                    return;
                } else {

                    user.setAdress(editTextadress.getText().toString());
                    user.setName(editTextname.getText().toString());
                    user.setEmail(editTextemail.getText().toString());
                    user.setPhone(editTextphone.getText().toString());
                    user.setFloor(editTextfloor.getText().toString());
                    user.setDoor(editTextdoor.getText().toString());
                    user.setPassword(editTextpass.getText().toString());



                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(
                                    editTextemail.getText().toString(),
                                    editTextpass.getText().toString()

                            ).addOnCompleteListener(task -> {
                        System.out.println(FirebaseAuth.getInstance().getCurrentUser());
                        if (task.isSuccessful()) {
                            database=FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/");
                            ref=database.getReference("Users").push();
                            ref.setValue(user);

                            Intent intent = new Intent(Register.this,LogIn.class);
                            startActivity(intent);
                        } else {
                            System.out.println(editTextpass.getText().toString());
                            Toast.makeText(getBaseContext(), "Pass required",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });


                }

            }
        });
    }
    @Override public void onBackPressed() {
        Intent intent=new Intent(this, LogIn.class);
        startActivity(intent);
    }



}
