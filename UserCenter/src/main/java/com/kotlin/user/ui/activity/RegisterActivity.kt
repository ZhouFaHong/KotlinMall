package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.common.AppManager
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import dagger.internal.DaggerCollections
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Retrofit


class RegisterActivity : BaseMvpActivity<RegisterPresenter>() , RegisterView {

    private var pressTime:Long = 0

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }

    override fun onLoginResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()
        registerBtn.setOnClickListener {

            mPresenter.register(phone.text.toString(),code.text.toString(),password1.text.toString())
//            Toast.makeText(this,"注册",Toast.LENGTH_SHORT).show()
//            toast("注册")
//            startActivity(intentFor<TestActivity>("id" to 77))
//            startActivity<TestActivity>("id" to 10)

        }

        sendCode.onClick {
            println("----------------test------------------------")

            mPresenter.example()
//            startActivity<TestActivity>("id" to 10)
        }

        loginBtn.onClick {
            mPresenter.login("admin","admin123456")
//            mPresenter.login("Noah","888888")

        }
    }

    private fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)

        mPresenter.mView = this
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000){
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }
}