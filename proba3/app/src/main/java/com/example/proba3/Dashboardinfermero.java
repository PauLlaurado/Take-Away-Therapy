package com.example.proba3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Dashboardinfermero extends AppCompatActivity {

    private String myText;
    private DatabaseReference mDatabase;
    private ArrayList<Infermeros>infermeroslist=new ArrayList<>();
    private ArrayList<String>stringid=new ArrayList<>();
    String a="";
    Infermeros infermeroupdate=new Infermeros();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardinfermero);
        System.out.println("dasok");
        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Infermeros");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Infermeros infermeros = new Infermeros();
                    infermeros = singleSnapshot.getValue(Infermeros.class);
                    infermeroslist.add(infermeros);
                    stringid.add(singleSnapshot.getKey());
                    System.out.println("adosoiasd");
                }
                System.out.println(infermeroslist.toString()+" asd sa da ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    void alertDialogDemo() {
        final EditText editText=new EditText(this);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Escribe una breve descripcion tuya") .setTitle("Descripcion")
                .setCancelable(false);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(editText);
        System.out.println();
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Infermeros infermeroupdate=new Infermeros();
               myText=editText.getText().toString();

//                String intentstr=getIntent().getStringExtra("email");
                String intentstr="infermero@gmail.com";
                System.out.println(infermeroslist.size());
                for (int i = 0; i <infermeroslist.size() ; i++) {
                    System.out.println(infermeroslist.get(i).getEmail()+" "+intentstr);
                    if (infermeroslist.get(i).getEmail().equals(intentstr)){
                        a=stringid.get(i);
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
        alertDialogDemo();

    }

    @Override public void onBackPressed() {
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
