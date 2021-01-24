package com.example.traveller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.traveller.R;
import com.example.traveller.util.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FindFragment extends Fragment {
    private GridView gridView;
    private List<Map<String,Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        setGirdView(view);


        return view;
    }

    private void setGirdView(View view) {
        gridView = view.findViewById(R.id.grids_find);
        list = new ArrayList<>();
        for(int i = 0;i< DataUtil.find_menu_imgs.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("image",DataUtil.find_menu_imgs[i]);
            map.put("text",DataUtil.find_menu_txt[i]);
            list.add(map);
        }

        String from[] = {"image","text"};
        int to[] = {R.id.find_menu_image,R.id.find_menu_text};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list,R.layout.find_menu_item,from,to);
        gridView.setAdapter(simpleAdapter);


    }
}