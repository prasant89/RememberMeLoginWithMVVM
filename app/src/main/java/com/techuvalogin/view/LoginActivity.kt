package com.techuvalogin.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techuvalogin.utility.PreferenceHelper
import com.techuvalogin.utility.PreferenceHelper.isRemember
import com.techuvalogin.utility.PreferenceHelper.password
import com.techuvalogin.utility.PreferenceHelper.userName
import com.techuvalogin.utility.showShortToast
import com.techuvalogin.view.databinding.ActivityLoginBinding
import com.techuvalogin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    val USER_PREF_NAME_PWD = "Login_data"
    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBind: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        context = this@LoginActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val prefs = PreferenceHelper.customPreference(this, USER_PREF_NAME_PWD)

        isRememberMe(dataBind, prefs)

        dataBind.cheRemember?.setOnCheckedChangeListener { buttonView, isChecked ->
           // val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " this Check it Checkbox."
           // showShortToast("$msg")
            if (isChecked) dataBind.cheRemember?.isChecked else dataBind.cheRemember?.isChecked
            prefs.isRemember = isChecked

        }

        dataBind.buttonSignIn.setOnClickListener {
            strUsername = dataBind.editTextEmail.text.toString().trim()
            strPassword = dataBind.editTextPassword.text.toString().trim()
            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {
                if (it == null) {
                    showShortToast("Data Not Found")
                } else {
                    prefs.userName = it.Username
                    prefs.password = it.Password
                    if(dataBind.editTextEmail.text.toString().trim().equals(it.Username)&&dataBind.editTextPassword.text.toString().trim().equals(it.Password))
                        showShortToast("Login Successfully")
                    var intent=Intent(this@LoginActivity,DashbordPageActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    private fun isRememberMe(dataBind: ActivityLoginBinding, prefs: SharedPreferences) {
        if (prefs.isRemember) {
            dataBind.cheRemember!!.isChecked = true
        } else {
            dataBind.cheRemember!!.isChecked = false
        }
    }


    fun SignUpAccount(view: View) {
        val intent = Intent(this@LoginActivity, SignUpActivity::class.java) // (1) (2)
        startActivity(intent)
    }

}