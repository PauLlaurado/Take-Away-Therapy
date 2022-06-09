package com.example.proba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proba3.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menulateral,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
               Intent intent=new Intent(MainActivity.this,Perfil.class);
                startActivity(intent);
break;
            case R.id.map:

                 intent=new Intent(MainActivity.this,MapsActivity.class);
                 startActivity(intent);
                break;
            case R.id.errors:
                intent=new Intent(MainActivity.this,Send_Errors.class);
                startActivity(intent);
break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                intent=new Intent(MainActivity.this,LogIn.class);
                startActivity(intent);

                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return Boolean.parseBoolean(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }
    @Override public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
      Intent  intent=new Intent(MainActivity.this,LogIn.class);
        startActivity(intent);
    }

}