package com.example.administrator.shoppingproject.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shoppingproject.Base.Data;
import com.example.administrator.shoppingproject.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class XRecyclerAdpater extends XRecyclerView.Adapter<XRecyclerAdpater.MyholderX> {
    ArrayList<Data> arr;
    Context text;

    public XRecyclerAdpater(ArrayList<Data> arr, Context text) {
        this.arr = arr;
        this.text = text;
    }
    @NonNull
    @Override
    public MyholderX onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(text).inflate(R.layout.homehen,null);
        return new MyholderX(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyholderX myholderX, int i) {
        Picasso.with(text).load(arr.get(i).getPic()).into(myholderX.im_pic);
        myholderX.tv1_tittle.setText(arr.get(i).getTitle());
        myholderX.tv2_Text.setText(arr.get(i).getFood_str());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class MyholderX extends XRecyclerView.ViewHolder{
        TextView tv1_tittle,tv2_Text;
        ImageView im_pic;
        public MyholderX(@NonNull View itemView) {
            super(itemView);
            tv1_tittle=itemView.findViewById(R.id.tv1_home_tittle);
            tv2_Text=itemView.findViewById(R.id.tv2_home_Text);
            im_pic=itemView.findViewById(R.id.im_home);
        }
    }
}
