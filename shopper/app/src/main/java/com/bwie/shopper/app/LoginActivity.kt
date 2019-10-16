package com.atguigu.shoppingmall.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.bwie.shopper.R

class LoginActivity : Activity(), View.OnClickListener {
    private var ibLoginBack: ImageButton? = null
    private var etLoginPhone: EditText? = null
    private var etLoginPwd: EditText? = null
    private var ibLoginVisible: ImageButton? = null
    private var btnLogin: Button? = null
    private var tvLoginRegister: TextView? = null
    private var tvLoginForgetPwd: TextView? = null
    private var ib_weibo: ImageButton? = null
    private var ib_qq: ImageButton? = null
    private var ib_wechat: ImageButton? = null

    private var count: Int = 0
    private val screen_name: String? = null
    private val profile_image_url: String? = null

    /**
     * Find the Views in the layout<br></br>
     * <br></br>
     * Auto-created on 2016-10-12 21:26:53 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private fun findViews() {
        ibLoginBack = findViewById<View>(R.id.ib_login_back) as ImageButton
        etLoginPhone = findViewById<View>(R.id.et_login_phone) as EditText
        etLoginPwd = findViewById<View>(R.id.et_login_pwd) as EditText
        ibLoginVisible = findViewById<View>(R.id.ib_login_visible) as ImageButton
        btnLogin = findViewById<View>(R.id.btn_login) as Button
        tvLoginRegister = findViewById<View>(R.id.tv_login_register) as TextView
        tvLoginForgetPwd = findViewById<View>(R.id.tv_login_forget_pwd) as TextView
        ib_weibo = findViewById<View>(R.id.ib_weibo) as ImageButton
        ib_qq = findViewById<View>(R.id.ib_qq) as ImageButton
        ib_wechat = findViewById<View>(R.id.ib_wechat) as ImageButton

        ibLoginBack!!.setOnClickListener(this)
        ibLoginVisible!!.setOnClickListener(this)
        btnLogin!!.setOnClickListener(this)
        ib_weibo!!.setOnClickListener(this)
        ib_qq!!.setOnClickListener(this)
        ib_wechat!!.setOnClickListener(this)
    }

    /**
     * Handle button click events<br></br>
     * <br></br>
     * Auto-created on 2016-10-12 21:26:53 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    override fun onClick(v: View) {
        if (v === ibLoginBack) {
            finish()
        } else if (v === ibLoginVisible) {

            count++
            if (count % 2 == 0) {
                ibLoginVisible!!.setBackgroundResource(R.drawable.new_password_drawable_invisible)
                etLoginPwd!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                ibLoginVisible!!.setBackgroundResource(R.drawable.new_password_drawable_visible)
                etLoginPwd!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            }
        } else if (v === btnLogin) {

        } else if (v === ib_weibo) {
            //            mShareAPI = UMShareAPI.get(this);
            //            mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
        } else if (v === ib_qq) {
            //            mShareAPI = UMShareAPI.get(this);
            //            mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
        } else if (v === ib_wechat) {
            //            mShareAPI = UMShareAPI.get(this);
            //            mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViews()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
