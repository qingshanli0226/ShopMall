package com.atguigu.shoppingmall.community.bean

/**
 * Created by Administrator on 2016/10/6.
 */
class NewPostBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"post_id":"2409","user_id":"582363","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14757383639657991.png","saying":"","add_time":"1475738364","likes":"0","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"面对疾风吧","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":[]},{"post_id":"2408","user_id":"563046","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/1475725288195472.jpeg","saying":"发货啊！！！！发货！！！！","add_time":"1475725303","likes":"0","comments":"1","is_hot":"0","is_top":"0","is_essence":"0","username":"君酱","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14742039706486902.jpeg","is_like":"0","comment_list":["亲  自营现货商品已经恢复发货了，供应商的会在国庆假期结束后恢复发货的，具体的需要看供应商的假期安排哦"]},{"post_id":"2406","user_id":"543961","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756614250945495.jpeg","saying":"我想\u2026\u2026请问一下\u2026\u2026我叶的青梅会补货吗。QAQ想拿来凑单\u2026\u2026","add_time":"1475661508","likes":"0","comments":"6","is_hot":"0","is_top":"0","is_essence":"0","username":"-LICHT-","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14756620336796494.jpeg","is_like":"0","comment_list":["回复 ＠尚硅谷首席神秘官：嘻嘻谢谢建议！才不会告诉你重点并不是吃的呢。(~˘▾˘)~刚刚去瞄了两眼，好像没有什么种草的\u2026\u2026（不，这个人因为只是没钱orz）","凑单可以考虑别的零食哈，我们有很多进口零食也很不错的","回复 ＠尚硅谷首席铲屎官：QAQ好的谢谢。","回复 ＠-LICHT-：具体补货时间还不清楚哦","回复 ＠尚硅谷首席铲屎官：谢谢~那可以问一下大概要多久吗\u2026\u2026","亲 会补货的，亲可以先收藏哦，到货后就能及时购买了哦"]},{"post_id":"2405","user_id":"531274","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756593224672148.png","saying":"昨天下的单。好想问问什么时候发货的锁！！因为后天要走了！","add_time":"1475659322","likes":"0","comments":"2","is_hot":"0","is_top":"0","is_essence":"0","username":"Foehn","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14700074120478917.png","is_like":"0","comment_list":["回复尚硅谷首席铲屎官：好的 谢谢谷谷","亲 自营现货商品已经恢复发货了，供应商一般会在国庆假期结束后恢复发货的，具体的以供应商的假期安排为准哦(≧▽≦)"]},{"post_id":"2404","user_id":"582149","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756492642393210.jpeg","saying":"可约板绘或者手绘，手绘卡片邮寄到家\n","add_time":"1475649350","likes":"2","comments":"2","is_hot":"0","is_top":"0","is_essence":"0","username":"白白空空白空空","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":["把你QQ给我，如果哪天需要我会找你的(人´∀｀)♡","把你QQ给我，如果哪天需要我会找你的(人´∀｀)♡"]}]
     */

    var code: Int = 0
    var msg: String? = null
    /**
     * post_id : 2409
     * user_id : 582363
     * figure : http://img01.cycangcdn.com/ugc/post/img/201610/14757383639657991.png
     * saying :
     * add_time : 1475738364
     * likes : 0
     * comments : 0
     * is_hot : 0
     * is_top : 0
     * is_essence : 0
     * username : 面对疾风吧
     * avatar : http://s1.cycangcdn.com/img/user_icon.png
     * is_like : 0
     * comment_list : []
     */

    lateinit var result: List<ResultBean>

    class ResultBean {
        var post_id: String? = null
        var user_id: String? = null
        var figure: String? = null
        var saying: String? = null
        var add_time: String? = null
        var likes: String? = null
        var comments: String? = null
        var is_hot: String? = null
        var is_top: String? = null
        var is_essence: String? = null
        var username: String? = null
        var avatar: String? = null
        var is_like: String? = null
        var comment_list: List<*>? = null
    }
}
