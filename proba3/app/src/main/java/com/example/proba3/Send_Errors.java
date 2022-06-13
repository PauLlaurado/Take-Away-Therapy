package com.example.proba3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proba3.objetos.SendEmail;
import com.google.firebase.auth.FirebaseAuth;

public class Send_Errors extends AppCompatActivity {

    private EditText editTextError;
    private TextView tvemail;
    private Button buttonSendError;
    private String stringEmail, stringReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senderrors);


        tvemail = findViewById(R.id.editText_Error_Email);
        editTextError = findViewById(R.id.editText_Errors_RepError);
        buttonSendError = findViewById(R.id.button_Errors_SendError);

        tvemail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        buttonSendError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringReport = editTextError.getText().toString();


                Toast.makeText(Send_Errors.this, "Error enviat", Toast.LENGTH_SHORT).show();

                String mensaje = "El usuario: " + FirebaseAuth.getInstance().getCurrentUser().getEmail() + " quiere reportar un error.\nError: " + stringReport;

                new SendEmail(Send_Errors.this, "takeawaytherapy@gmail.com", "Error", mensaje).execute();


            }
        });

    }
}
