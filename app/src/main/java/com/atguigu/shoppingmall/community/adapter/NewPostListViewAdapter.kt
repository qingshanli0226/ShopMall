package com.atguigu.shoppingmall.community.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.community.bean.NewPostBean
import com.atguigu.shoppingmall.utils.BitmapUtils
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide
import com.opendanmaku.DanmakuItem
import com.opendanmaku.DanmakuView
import com.opendanmaku.IDanmakuItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

import java.util.ArrayList
import java.util.Collections

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Administrator on 2016/10/6.
 */
class NewPostListViewAdapter(private val mContext: Context, private val result: List<NewPostBean.ResultBean>) : BaseAdapter() {
    private var comment_list: List<String>? = null

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
            convertView = View.inflate(mContext, R.layout.item_listview_newpost, null)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val resultBean = result[position]
        holder.tvCommunityUsername!!.text = resultBean.username
        //holder.tvCommunityAddtime.setText();
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + resultBean.figure!!)
                .into(holder.ivCommunityFigure!!)


        holder.tvCommunitySaying!!.text = resultBean.saying
        holder.tvCommunityLikes!!.text = resultBean.likes
        holder.tvCommunityComments!!.text = resultBean.comments

        Picasso.with(mContext).load(resultBean.avatar).transform(object : Transformation {
            override fun transform(bitmap: Bitmap): Bitmap {
                //先对图片进行压缩
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

        //设置弹幕
        comment_list = resultBean.comment_list as List<String>
        if (comment_list != null && comment_list!!.size > 0) {
            holder.danmakuView!!.visibility = View.VISIBLE

            val list = ArrayList<IDanmakuItem>()
            for (i in comment_list!!.indices) {
                val item = DanmakuItem(mContext, comment_list!![i], holder.danmakuView!!.width)
                list.add(item)
            }
            Collections.shuffle(comment_list)
            holder.danmakuView!!.addItem(list, true)
            holder.danmakuView!!.show()
        } else {
            holder.danmakuView!!.visibility = View.GONE
        }
        return convertView
    }


    internal class ViewHolder(view: View) {
        var tvCommunityUsername: TextView? = view.findViewById(R.id.tv_community_username)
        var tvCommunityAddtime: TextView? =  view.findViewById(R.id.tv_community_addtime)
        var rl: RelativeLayout? = view.findViewById(R.id.rl)
        var ivCommunityFigure: ImageView? = view.findViewById(R.id.iv_community_figure)
        var danmakuView: DanmakuView? = view.findViewById(R.id.danmakuView)
        var tvCommunitySaying: TextView? = view.findViewById(R.id.tv_community_saying)
        var tvCommunityLikes: TextView? = view.findViewById(R.id.tv_community_likes)
        var tvCommunityComments: TextView? = view.findViewById(R.id.tv_community_comments)
        var ibNewPostAvatar: ImageButton? = view.findViewById(R.id.ib_new_post_avatar)

        init {
            ButterKnife.bind(this, view)
        }
    }
}
