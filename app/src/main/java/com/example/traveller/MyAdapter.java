package com.example.traveller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveller.util.DataUtil;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    String data[];
    int images[];
    Context context;




    public MyAdapter(Context ct,String s[],int img[]){
        context = ct;
        data = s;
        images = img;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toutiao,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MyViewHolder h = (MyViewHolder)holder;
        h.imageView.setBackgroundResource(images[position]);
        h.title.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View v;
        TextView title;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            title = v.findViewById(R.id.title_toutiao);
            imageView = v.findViewById(R.id.image_find_toutiao);
        }
    }
}
