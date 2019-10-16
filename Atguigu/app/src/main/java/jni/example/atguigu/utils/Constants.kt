package jni.example.atguigu.utils

object Constants {
    //系统默认的模拟器用这个ID
    val BASE :String = "http://192.168.100.1:8080"
    //内网用这个ID
    val BASEN:String = "http://169.254.172.7:8080"
    //请求Json串用这个ID
    val BASE_URL_JSON:String = "/atguigu/json"

    //首页UI串
    val URL_HOME:String = "$BASE_URL_JSON/HOME_URL.json"
    //请求图片基本URL
    val BASE_URl_IMAGE:String = "$BASE/atguigu/img"

    //小裙子
    val SKIRT_URL = BASE_URL_JSON+"SKIRT_URL.json"
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
}