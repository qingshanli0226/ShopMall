package com.atguigu.shoppingmall.community.bean

/**
 * Created by Administrator on 2016/10/6.
 */
class HotPostBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"post_id":"2313","user_id":"90437","figure":"http://img01.cycangcdn.com/ugc/post/img/201609/14746252609717297.png","saying":"惊喜不断，新爆款、爆款、爆款，限时预定哦！","add_time":"1474625262","likes":"13","comments":"6","is_hot":"1","is_top":"1","is_essence":"1","username":"尚硅谷首席惊喜官","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png","is_like":"0","comment_list":["啦啦","啦啦","吃土快乐，祝我快乐","猝不及防一口土\u2026\u2026","吃土愉快","啊啊啊啊啊啊啊疯掉了！！！！！猝不及防啊啊啊"]},{"post_id":"2199","user_id":"90437","figure":"http://img01.cycangcdn.com/ugc/post/img/201609/14736747792594015.png","saying":"谷的新品-流烟昔泠新款套装【轻梦泽】今晚八点准时在尚硅谷首发，买买买2333\u2026","add_time":"1473674779","likes":"15","comments":"2","is_hot":"1","is_top":"1","is_essence":"1","username":"尚硅谷首席惊喜官","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png","is_like":"0","comment_list":["第二！","我第一～～哈哈哈～～沙发～"]},{"post_id":"2345","user_id":"90437","figure":"http://img01.cycangcdn.com/ugc/post/img/201609/14751401203006663.png","saying":"特大惊喜：#谷の盛典# #小谷陪你过国庆# 国庆攻略大曝光！小谷教您买买买！0元备战黄金周~30日前海量优惠券免！费！领！假期福利满天飞~全场满减搭配优惠券！爆款直降白菜价！","add_time":"1475140120","likes":"3","comments":"10","is_hot":"1","is_top":"1","is_essence":"1","username":"尚硅谷首席惊喜官","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png","is_like":"0","comment_list":["回复尚硅谷首席铲屎官：wom","回复尚硅谷首席铲屎官：wom","ilcd","回复 ＠夕夕夕凄：亲 通过首页的\u201c小谷陪你过国庆\u201d的banner进去就可以参加大转盘抽奖了*^_^*","哎找不到大转盘哎","回复 ＠小鸟～萌萌哒：大转盘9月30日18点上线哦","大转盘在哪里找呀？～～～","回复二次元哒小神棍：满减是可以配合优惠券一起叠加使用的","回复二次元哒小神棍：国庆我们有满减，这个才是重头啦，优惠券这次国庆不是主角！","优惠券的力度减小了，有些商品除了小谷自营的商品不划算。就算有免单的机会也不一定能抽到啊！"]},{"post_id":"2278","user_id":"90437","figure":"http://img01.cycangcdn.com/ugc/post/img/201609/14742827198764639.png","saying":"#谷の闪购# 机智菌爷的判断从来是非常准确的~\r\n食欲之秋已经到来！宝宝们绝对需要今天的大安利！【麦大叔】美味零食~开启超爽8折大狂欢！肉肉肉什么的一波带走！开心果大把大把地塞！一起来放纵吃吃吃吧！","add_time":"1474282720","likes":"2","comments":"5","is_hot":"1","is_top":"1","is_essence":"1","username":"尚硅谷首席惊喜官","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png","is_like":"0","comment_list":["恶魔","啊啊啊啊","啊啊啊啊"]},{"post_id":"2350","user_id":"90437","figure":"http://img01.cycangcdn.com/ugc/post/img/201609/14752273661625367.png","saying":"#谷の爆款# 告别了炎夏~小谷内心依然惦记着海边的美好画面！【全职高手】4款新品PU小包包！带你寻味夏季！激萌指数直线飙升！浅蓝色配色充满海洋的气息！趁着国庆优惠把他们一同带回家过节吧~","add_time":"1475227367","likes":"7","comments":"0","is_hot":"1","is_top":"1","is_essence":"1","username":"尚硅谷首席惊喜官","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png","is_like":"0","comment_list":[]}]
     */

    var code: Int = 0
    var msg: String? = null
    /**
     * post_id : 2313
     * user_id : 90437
     * figure : http://img01.cycangcdn.com/ugc/post/img/201609/14746252609717297.png
     * saying : 惊喜不断，新爆款、爆款、爆款，限时预定哦！
     * add_time : 1474625262
     * likes : 13
     * comments : 6
     * is_hot : 1
     * is_top : 1
     * is_essence : 1
     * username : 尚硅谷首席惊喜官
     * avatar : http://img01.cycangcdn.com/ugc/user/avatar/14651924470798522.png
     * is_like : 0
     * comment_list : ["啦啦","啦啦","吃土快乐，祝我快乐","猝不及防一口土\u2026\u2026","吃土愉快","啊啊啊啊啊啊啊疯掉了！！！！！猝不及防啊啊啊"]
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
        var comment_list: List<String>? = null
    }
}
