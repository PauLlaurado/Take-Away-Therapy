package com.example.proba3.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.Infermeros;
import com.example.proba3.R;
import com.example.proba3.databinding.FragmentNotificationsBinding;
import com.example.proba3.ui.dashboard.AdapterDash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    RecyclerView recyclerView;
    AdapterNot adapterNot;
    private ArrayList<Infermeros> infermeroslist = new ArrayList<>();
    Infermeros infermeros, infermeros2, infermeros3, infermeros4, infermeros5, infermeros6, infermeros7, infermeros8;
    private DatabaseReference mDatabase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerviewcontratar);

        infermeros = new Infermeros("Pau LLaurado Moreno", "10 ", "chico rubia alto");
        infermeros2 = new Infermeros("Pau Garcia", "10 ", "chico rubia alto");
        infermeros3 = new Infermeros("Pau Moreno", "10 ", "chico rubia alto");
        infermeros4 = new Infermeros("Pau ", "10 ", "chico rubia alto");
        infermeros5 = new Infermeros("Pau", "10 ", "chico rubia alto");
        infermeros6 = new Infermeros("Pau", "10 ", "chico rubia alto");
        infermeros7 = new Infermeros("Pau", "10 ", "chico rubia alto");
        infermeros8 = new Infermeros("Pau", "10 ", "chico rubia alto");


        infermeroslist.add(infermeros);
        infermeroslist.add(infermeros2);
        infermeroslist.add(infermeros3);
        infermeroslist.add(infermeros4);
        infermeroslist.add(infermeros5);
        infermeroslist.add(infermeros6);
        infermeroslist.add(infermeros7);
        infermeroslist.add(infermeros8);

//        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Infermeros");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
//                    Infermeros infermeros = new Infermeros();
//                    infermeros = singleSnapshot.getValue(Infermeros.class);
//                    infermeroslist.add(infermeros);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterNot = new AdapterNot(infermeroslist);
        recyclerView.setAdapter(adapterNot);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}