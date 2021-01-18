package com.example.traveller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.traveller.fragment.FindFragment;
import com.example.traveller.fragment.IndexFragment;
import com.example.traveller.fragment.MeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new IndexFragment());
        fragmentTransaction.commit();

    }




    public void myClick(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.index:
                fragmentTransaction.replace(R.id.container,new IndexFragment());
                break;
            case R.id.find:
                fragmentTransaction.replace(R.id.container,new FindFragment());
                break;
            case R.id.me:
                fragmentTransaction.replace(R.id.container,new MeFragment());
                break;
        }
        fragmentTransaction.commit();
    }
}