package com.example.traveller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.example.traveller.MyAdapter;
import com.example.traveller.R;
import com.example.traveller.util.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FindFragment extends Fragment {
    private GridView gridView;
    private List<Map<String,Object>> list;
    private RecyclerView recyclerView;
    String title[];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        setGirdView(view);
        setRecyclerView(view);



        return view;
    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.toutiao_view);
        title = getResources().getStringArray(R.array.title);
        MyAdapter myAdapter = new MyAdapter(getContext(),title,DataUtil.find_toutiao_imgs);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
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