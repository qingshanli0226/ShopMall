package com.atguigu.shoppingmall.community.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.community.bean.HotPostBean
import com.atguigu.shoppingmall.utils.BitmapUtils
import com.atguigu.shoppingmall.utils.Constants
import com.atguigu.shoppingmall.utils.DensityUtil
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

import java.text.SimpleDateFormat

import butterknife.BindView
import butterknife.ButterKnife

class HotPostListViewAdapter(private val mContext: Context, private val result: List<HotPostBean.ResultBean>) : BaseAdapter() {

    override fun getCount(): Int {
        return result.size
    }

    override fun getItem(position: Int): Any {
        return result[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val resultBean = result[position]
        holder.tvHotUsername!!.text = resultBean.username

        val myFmt = SimpleDateFormat("MM-dd HH:mm")
        holder.tvHotAddtime!!.text = myFmt.format(Integer.parseInt(resultBean.add_time!!))

        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + resultBean.figure!!).into(holder.ivHotFigure!!)
        holder.tvHotSaying!!.text = resultBean.saying
        holder.tvHotLikes!!.text = resultBean.likes
        holder.tvHotComments!!.text = resultBean.comments

        Picasso.with(mContext).load(resultBean.avatar).transform(object : Transformation {
            override fun transform(bitmap: Bitmap): Bitmap {
                //先对图片进行压缩
                //Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                val zoom = BitmapUtils.zoom(bitmap, 70f, 70f)
                //对请求回来的Bitmap进行圆形处理
                val ciceBitMap = BitmapUtils.circleBitmap(zoom)
                bitmap.recycle()//必须队更改之前的进行回收
                return ciceBitMap
            }

            override fun key(): String {
                return ""
            }
        }).into(holder.ibNewPostAvatar)

        val is_top = resultBean.is_top

        if ("1" == is_top) {
            val textViewLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val textView = TextView(mContext)
            textView.text = "置顶"
            textViewLp.setMargins(DensityUtil.dip2px(mContext, 10f), 0, DensityUtil.dip2px(mContext, 5f), 0)
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setBackgroundResource(R.drawable.is_top_shape)
            textView.setPadding(DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f))
            holder.llHotPost!!.removeAllViews()
            holder.llHotPost!!.addView(textView, textViewLp)
        }
        val is_hot = resultBean.is_hot
        if ("1" == is_hot) {
            val textViewLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val textView = TextView(mContext)
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5f), 0)
            textView.text = "热门"
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setPadding(DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f))
            textView.setBackgroundResource(R.drawable.is_hot_shape)
            holder.llHotPost!!.addView(textView, textViewLp)
        }
        val is_essence = resultBean.is_essence
        if ("1" == is_essence) {
            val textViewLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5f), 0)
            val textView = TextView(mContext)
            textView.text = "精华"
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setPadding(DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 5f))
            textView.setBackgroundResource(R.drawable.is_essence_shape)
            holder.llHotPost!!.addView(textView, textViewLp)
        }
        return convertView
    }

    internal class ViewHolder(view: View) {
        var tvHotUsername: TextView? = view.findViewById(R.id.tv_hot_username)
        var tvHotAddtime: TextView? = view.findViewById(R.id.tv_hot_addtime)
        var ivHotFigure: ImageView? = view.findViewById(R.id.iv_hot_figure)
        var llHotPost: LinearLayout? = view.findViewById(R.id.ll_hot_post)
        var tvHotSaying: TextView? = view.findViewById(R.id.tv_hot_saying)
        var tvHotLikes: TextView? = view.findViewById(R.id.tv_hot_likes)
        var tvHotComments: TextView? = view.findViewById(R.id.tv_hot_comments)
        var ibNewPostAvatar: ImageButton? = view.findViewById(R.id.ib_new_post_avatar)

        init {
            ButterKnife.bind(this, view)
        }
    }
}
