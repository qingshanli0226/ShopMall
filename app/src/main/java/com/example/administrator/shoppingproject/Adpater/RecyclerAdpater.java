package com.example.administrator.shoppingproject.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shoppingproject.Base.Data;
import com.example.administrator.shoppingproject.Base.RecyclerBean;
import com.example.administrator.shoppingproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdpater extends RecyclerView.Adapter<RecyclerAdpater.Myhodler> {
    ArrayList<Data> arr;
    Context text;

    public RecyclerAdpater(ArrayList<Data> arr, Context text) {
        this.arr = arr;
        this.text = text;
    }

    @NonNull
    @Override
    public Myhodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(text).inflate(R.layout.homehen,null);
        return new Myhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myhodler myhodler, int i) {
        Picasso.with(text).load(arr.get(i).getPic()).into(myhodler.im_pic);
        myhodler.tv1_tittle.setText(arr.get(i).getTitle());
        myhodler.tv2_Text.setText(arr.get(i).getFood_str());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class Myhodler extends RecyclerView.ViewHolder{
        TextView tv1_tittle,tv2_Text;
        ImageView im_pic;
        public Myhodler(@NonNull View itemView) {
            super(itemView);
            tv1_tittle=itemView.findViewById(R.id.tv1_home_tittle);
            tv2_Text=itemView.findViewById(R.id.tv2_home_Text);
            im_pic=itemView.findViewById(R.id.im_home);
        }
    }
}
