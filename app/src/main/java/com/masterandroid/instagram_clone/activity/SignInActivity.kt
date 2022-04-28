package com.masterandroid.instagram_clone.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.masterandroid.instagram_clone.R
import com.masterandroid.instagram_clone.managers.handler.AuthHandler
import com.masterandroid.instagram_clone.managers.AuthManager
import com.masterandroid.instagram_clone.managers.DatabaseManager
import com.masterandroid.instagram_clone.managers.PrefsManager
import com.masterandroid.instagram_clone.managers.handler.DBUserHandler
import com.masterandroid.instagram_clone.model.User
import com.masterandroid.instagram_clone.utils.Extensions.toast
import com.masterandroid.instagram_clone.utils.Logger
import java.lang.Exception

class SignInActivity : BaseActivity() {
    val TAG = SignInActivity::class.java.simpleName

    lateinit var et_email :EditText
    lateinit var et_password :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initViews()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)

        val b_signin = findViewById<Button>(R.id.b_signin)
        b_signin.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            firebaseSignIn(email, password)
        }
        val tv_signup = findViewById<TextView>(R.id.tv_signup)
        tv_signup.setOnClickListener { callSignUpActivity() }
    }

    private fun firebaseSignIn(email: String, password: String) {
        showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess(uid: String) {
                dismissLoading()
                toast(getString(R.string.str_signin_success))
                storeDeviceTokenToUser()            }


            override fun onError(exception: Exception?) {
                dismissLoading()
                Logger.e(TAG,exception.toString())
                toast(getString(R.string.str_signin_failed))
            }
        })
    }

    private fun storeDeviceTokenToUser() {
        val deviceToken = PrefsManager(this).loadDeviceToken()
        val uid = AuthManager.currentUser()!!.uid
        DatabaseManager.addMyDeviceToken(uid, deviceToken, object : DBUserHandler {
            override fun onSuccess(user: User?) {
                callMainActivity(this@SignInActivity)
            }

            override fun onError(e: Exception) {
                callMainActivity(this@SignInActivity)
            }

        })

    }




}