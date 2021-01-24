package com.example.traveller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.example.traveller.R;
import com.example.traveller.util.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexFragment extends Fragment {
    private ViewPager2 pager;
    private LinearLayout pointers;
    private int current_index;
    private GridView gridView;
    private List<Map<String,Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        pager = view.findViewById(R.id.pager);
        pointers = view.findViewById(R.id.pointers);
        current_index = 0;

        setViewPager();
        setGridView(view);



        return view;
    }

    private void setGridView(View view) {
        gridView = view.findViewById(R.id.grids);

        //初始化数据
        list = new ArrayList<>();
        for(int i = 0;i<DataUtil.index_menu_imgs.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",DataUtil.index_menu_imgs[i]);
            map.put("text",DataUtil.index_menu_txt[i]);
            list.add(map);
        }

        //设置参数
        String from[] = {"image","text"};
        int to[] ={R.id.index_menu_image,R.id.index_menu_text};

        SimpleAdapter adapter = new SimpleAdapter(getContext(),list,R.layout.index_menu_item,from,to);
        gridView.setAdapter(adapter);


    }

    private void setViewPager() {

        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder h = (ViewHolder) holder;
                h.bannber_container.setBackgroundResource(DataUtil.pics[position%4]);

            }

            @Override
            public int getItemCount() {
                return Integer.MAX_VALUE;
            }
        };

        pager.setAdapter(adapter);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //Log.v("TAG","changed1");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.v("TAG","changed2");
                for(int i = 0;i<pointers.getChildCount();i++){
                    ((ImageView)pointers.getChildAt(i)).setBackgroundResource(R.drawable.dot_unselected);
                }
                ((ImageView)pointers.getChildAt(position%4)).setBackgroundResource(R.drawable.dot_selected);
                current_index = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                //Log.v("TAG","changed3");
            }
        });

        //开启一个线程控制每三秒切换一个广告
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(3000);
                        current_index++;
                        for(int i = 0;i<pointers.getChildCount();i++){
                            if(current_index%4 == i){
                                ((ImageView)pointers.getChildAt(i)).setBackgroundResource(R.drawable.dot_selected);
                                pager.setCurrentItem(current_index);
                                Log.v("TAG","current : "+current_index);
                            }else{
                                ((ImageView)pointers.getChildAt(i)).setBackgroundResource(R.drawable.dot_unselected);
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public LinearLayout bannber_container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            bannber_container = v.findViewById(R.id.bannber_container);


        }
    }
}