package iftm.filipe.com.iftransparenciav2.ui.activity

import android.content.Intent
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getContentLayout(): Int = R.layout.activity_login
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        super.onCreate(savedInstanceState)

        mViewDataBinding.loginButton.setReadPermissions("email")
        mViewDataBinding.loginButton.setReadPermissions("public_profile")


        mViewDataBinding.loginButton.setOnClickListener {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult?) {
                            print(result.toString())
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }

                        override fun onCancel() {
                            print("camceç")
                        }

                        override fun onError(error: FacebookException?) {
                            print("Falha")
                        }


                    })
        }


    }


}