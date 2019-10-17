package jni.example.atguigu.utils

object Constants {
    //    public static final String BASE = "http://192.168.51.104:8080";
    //系统默认的模拟器就用这个ip
    val BASE = "http://169.254.104.8:8080"
    //使用第三方模拟器--当前电脑的ip地址
    //    public static final String BASE = "http://192.168.51.104:8080";
    //运行到自己的真实手机上：
    //1.装一个共享wifi的软件-猎豹wifi-把本地电脑分享出一个wifi
    //2.tomcat是开启的
    //3.ip地址修改成wifi的ip地址
    //4.手机一定要连接电脑分享的wifi
    //    public static final String BASE = "http://192.168.191.1:8080";

    // 请求Json数据基本URL
    val BASE_URL_JSON = "$BASE/atguigu/json/"

    // 请求图片基本URL
    val BASE_URl_IMAGE = "$BASE/atguigu/img"


    //小裙子
    val SKIRT_URL = BASE_URL_JSON + "SKIRT_URL.json"
    //上衣
    val JACKET_URL = BASE_URL_JSON + "JACKET_URL.json"
    //下装(裤子)
    val PANTS_URL = BASE_URL_JSON + "PANTS_URL.json"
    //外套
    val OVERCOAT_URL = BASE_URL_JSON + "OVERCOAT_URL.json"
    //配件
    val ACCESSORY_URL = BASE_URL_JSON + "ACCESSORY_URL.json"
    //包包
    val BAG_URL = BASE_URL_JSON + "BAG_URL.json"
    //装扮
    val DRESS_UP_URL = BASE_URL_JSON + "DRESS_UP_URL.json"
    //居家宅品
    val HOME_PRODUCTS_URL = BASE_URL_JSON + "HOME_PRODUCTS_URL.json"
    //办公文具
    val STATIONERY_URL = BASE_URL_JSON + "STATIONERY_URL.json"
    //数码周边
    val DIGIT_URL = BASE_URL_JSON + "DIGIT_URL.json"
    //游戏专区
    val GAME_URL = BASE_URL_JSON + "GAME_URL.json"


    //主页Fragment路径
    val HOME_URL = BASE_URL_JSON + "HOME_URL.json"
    //分类Fragment里面的标签Fragment页面数据
    val TAG_URL = BASE_URL_JSON + "TAG_URL.json"


    val NEW_POST_URL = BASE_URL_JSON + "NEW_POST_URL.json"
    val HOT_POST_URL = BASE_URL_JSON + "HOT_POST_URL.json"


    //页面的具体数据的id
    val GOODSINFO_URL = BASE_URL_JSON + "GOODSINFO_URL.json"

    //服饰
    val CLOSE_STORE = BASE_URL_JSON + "CLOSE_STORE.json"
    //游戏
    val GAME_STORE = BASE_URL_JSON + "GAME_STORE.json"
    //动漫
    val COMIC_STORE = BASE_URL_JSON + "COMIC_STORE.json"
    //cosplay
    val COSPLAY_STORE = BASE_URL_JSON + "COSPLAY_STORE.json"
    //古风
    val GUFENG_STORE = BASE_URL_JSON + "GUFENG_STORE.json"
    //漫展
    val STICK_STORE = BASE_URL_JSON + "STICK_STORE.json"
    //文具
    val WENJU_STORE = BASE_URL_JSON + "WENJU_STORE.json"
    //零食
    val FOOD_STORE = BASE_URL_JSON + "FOOD_STORE.json"
    //首饰厂
    val SHOUSHI_STORE = BASE_URL_JSON + "SHOUSHI_STORE.json"


    var isBackHome: Boolean? = false


    //客服数据
    val CALL_CENTER = "http://www6.53kf.com/webCompany.php?arg=10007377&style=1&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fwww.atguigu.com%2Fcontant.shtml&keyword=&tfrom=1&tpl=crystal_blue&timeStamp=1479001706368&ucust_id="


}