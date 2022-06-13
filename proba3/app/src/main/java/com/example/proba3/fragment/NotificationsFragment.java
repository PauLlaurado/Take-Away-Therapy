package com.example.proba3.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proba3.adapter.AdapterNot;
import com.example.proba3.objetos.Infermeros;
import com.example.proba3.R;
import com.example.proba3.databinding.FragmentNotificationsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    RecyclerView recyclerView;
    AdapterNot adapterNot;
    private ArrayList<Infermeros> infermeroslist = new ArrayList<>();
    private DatabaseReference mDatabase;
    StorageReference storageRef = FirebaseStorage.getInstance("gs://projecte-73ca7.appspot.com").getReference();
    int i = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerviewcontratar);

        StorageReference ref = storageRef.child("infermers");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mDatabase = FirebaseDatabase.getInstance("https://projecte-73ca7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Infermeros");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Infermeros infermeros = new Infermeros();
                    infermeros = singleSnapshot.getValue(Infermeros.class);
                    infermeroslist.add(infermeros);
                }

                ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {

                        for (StorageReference item : listResult.getItems()) {
                            long MAXBYTES = 1024 * 1024;

                            item.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {

                                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    infermeroslist.get(i).setBitmap(bitmap);
                                    i++;
                                }
                            });

                        }
                        adapterNot = new AdapterNot(infermeroslist);
                        recyclerView.setAdapter(adapterNot);

                    }
                });
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