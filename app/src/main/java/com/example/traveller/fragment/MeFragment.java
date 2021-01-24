package com.example.traveller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.traveller.LoginActivity;
import com.example.traveller.R;
import com.example.traveller.util.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MeFragment extends Fragment {
    private List<Map<String,Object>> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_me, container, false);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        initData();

        ListView menuListView = view.findViewById(R.id.menu_listview);
        String[] from = {"img","txt"};
        int[] to ={R.id.menu_item_img,R.id.menu_item_text};
        SimpleAdapter adapter = new SimpleAdapter(getContext(),data,R.layout.me_menu_item,from,to);
        menuListView.setAdapter(adapter);
        return view;

    }

    private void initData() {
        for(int i = 0;i<9;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("img", DataUtil.me_imgs[i]);
            map.put("txt",DataUtil.me_txts[i]);
            data.add(map);

        }
    }
}