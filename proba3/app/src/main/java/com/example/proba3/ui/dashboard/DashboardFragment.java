package com.example.proba3.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proba3.Medicamentos;
import com.example.proba3.R;
import com.example.proba3.databinding.FragmentDashboardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<Medicamentos>medicamentoslist=new ArrayList<>();
    RecyclerView recyclerView;
    AdapterDash adapterDash;
    Button buy;
    private DatabaseReference mDatabase;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerviewbuy);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        buy=root.findViewById(R.id.comprar);

        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Medicamentos");

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                        Medicamentos medicamentos=new Medicamentos();
                        medicamentos=singleSnapshot.getValue(Medicamentos.class);
                        medicamentoslist.add(medicamentos);

                    }
                    adapterDash=new AdapterDash(medicamentoslist);
                    recyclerView.setAdapter(adapterDash);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}