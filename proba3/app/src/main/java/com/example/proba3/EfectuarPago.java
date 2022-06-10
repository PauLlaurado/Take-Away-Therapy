package com.example.proba3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EfectuarPago extends AppCompatActivity {

    String PUBLIC_KEY = "pk_test_51L5tTnAj9jQW6CmqWd7k9jri5AOaVIcYFTuLYZK8vmXtId3I8ju6h2ffNzazB8NKor7tdR30MV5h5mUxFlRTXfI900FCosKpsA";
    String PRIVATE_KEY = "sk_test_51L5tTnAj9jQW6Cmq9ME8cczPhBwTKBxsie1nV0dTm6kf4xiDReLzfm7YAp3ngx7UOWHa5j8D9PecwE8IrKnFBCxY00Np5iC8r9";

    PaymentSheet paymentSheet;

    String customerID;
    String EphericalKey;
    String ClientSecret;

    private TextView price;
    double preciodouble;
    private RadioButton radioButtonefectivo, radioButtontargeta;
    private Button gPay;
    String pre;
    String destinopedido;
    private DatabaseReference mDatabase;
    private ArrayList<Users>userslist=new ArrayList<>();
    String nombre;
    Pedido pedido=new Pedido();
    ArrayList<Medicamentos>medicamentoslist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        gPay = findViewById(R.id.paybutton);


        price = findViewById(R.id.detailPrice);
        medicamentoslist = (ArrayList<Medicamentos>) getIntent().getSerializableExtra("lista");
        destinopedido=getIntent().getStringExtra("destino");
        preciodouble = getIntent().getDoubleExtra("precio", 0);
        nombre=getIntent().getStringExtra("nombre");
        pre = String.valueOf(preciodouble);
        price.setText(pre + "€");
        radioButtonefectivo = findViewById(R.id.efectivo);
        radioButtontargeta = findViewById(R.id.card);

        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    Users users=new Users();
                    users=singleSnapshot.getValue(Users.class);
                    userslist.add(users);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for (int i = 0; i < userslist.size(); i++) {
            if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(userslist.get(i).getEmail())){

                pedido.setId("0");
                pedido.setAddress(userslist.get(i).getAdress()+" "+userslist.get(i).getFloor()+" "+userslist.get(i).getDoor());
            }
        }

        gPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButtontargeta.isChecked() == true) {
                    pedido.setPagado("pagado");
                    PaymentFlow();

                } else if (radioButtonefectivo.isChecked() == true) {
                    pedido.setPagado("efectivo");

                    Toast.makeText(EfectuarPago.this, "Pedido Realizado, faltara pagar", Toast.LENGTH_SHORT).show();

                }

            }

        });

        PaymentConfiguration.init(this, PUBLIC_KEY);

        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            onPaymentResult(paymentSheetResult);
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            customerID = object.getString("id");
                            Toast.makeText(EfectuarPago.this, customerID, Toast.LENGTH_SHORT).show();

                            getEphericalKey(customerID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + PRIVATE_KEY);
                return header;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EfectuarPago.this);
        requestQueue.add(stringRequest);


    }


    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment succes", Toast.LENGTH_SHORT).show();
        if (destinopedido.equals("comprar")){
            pedido.setContractat("comprar");
            pedido.setArrayListmedicamentos(medicamentoslist);
        }else if(destinopedido.equals("contratar")){
            pedido.setInfermer(nombre);
            pedido.setContractat("contractat");
            String servicio=getIntent().getStringExtra("servicio");
            pedido.setServicio(servicio);

        }

//            ArrayList<Medicamentos> medicamentoslist = (ArrayList<Medicamentos>) getIntent().getSerializableExtra("lista");
//            Toast.makeText(this, medicamentoslist.toString(), Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "Fail payment", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEphericalKey(String customerID) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            EphericalKey = object.getString("id");
                            Toast.makeText(EfectuarPago.this, EphericalKey, Toast.LENGTH_SHORT).show();

                            getClientSecret(customerID, EphericalKey);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + PRIVATE_KEY);
                header.put("Stripe-Version", "2020-08-27");
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EfectuarPago.this);
        requestQueue.add(stringRequest);
    }

    private void getClientSecret(String customerID, String ephericalKey) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            ClientSecret = object.getString("client_secret");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + PRIVATE_KEY);
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                List<Object> paymentMethodTypes =
                        new ArrayList<>();
                paymentMethodTypes.add("card");
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                params.put("amount", pre + "00");
                params.put("currency", "eur");
                params.put("automatic_payment_methods[enabled]", "true");


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EfectuarPago.this);
        requestQueue.add(stringRequest);


    }

    private void PaymentFlow() {

        paymentSheet.presentWithPaymentIntent(ClientSecret,
                new PaymentSheet.Configuration("ABC Company",
                        new PaymentSheet.CustomerConfiguration(
                                customerID,
                                EphericalKey
                        )));

    }
}
