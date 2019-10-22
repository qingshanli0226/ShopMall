package jni.example.atguigu.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import jni.example.atguigu.R;

public class Type_List_Left_Adapter extends BaseAdapter {
    private Context mContext;
    private int mSelect = 0;//选中项
    private String[] titles;

    public int getmSelect() {
        return mSelect;
    }

    public void setmSelect(int mSelect) {
        this.mSelect = mSelect;
        notifyDataSetChanged();
    }

    public Type_List_Left_Adapter(Context mContext, String[] titles) {
        this.mContext = mContext;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.type_list_left_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.type_left_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(titles[position]);

        if (mSelect == position) {
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);  //选中项背景
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"));
        } else {
            convertView.setBackgroundResource(R.drawable.bg2);  //其他项背景
            holder.tv_name.setTextColor(Color.parseColor("#323437"));
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tv_name;
    }
}
