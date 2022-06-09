package com.example.proba3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ContactUs extends AppCompatActivity {
    private EditText editTextWork,editTextemail;
    private Button button;
    private String stringEmail, stringReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);

        editTextemail = findViewById(R.id.editText_work);
        editTextWork = findViewById(R.id.editText_contact);
        button = findViewById(R.id.button_contactus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringReport = editTextWork.getText().toString();
                stringEmail=editTextemail.getText().toString();

                Toast.makeText(ContactUs.this, "Misatge enviat", Toast.LENGTH_SHORT).show();
String mensaje="El usuario: "+stringEmail+" quiere trabajar con nosotros.\nBreve explicacion.\nError: "+stringReport;

                new SendEmail(ContactUs.this, "takeawaytherapy@gmail.com", "Error", mensaje).execute();

            }
        });
    }
}
