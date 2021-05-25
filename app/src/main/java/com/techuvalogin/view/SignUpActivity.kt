package com.techuvalogin.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.techuvalogin.utility.showShortToast
import com.techuvalogin.view.databinding.ActivitySignUpBinding
import com.techuvalogin.viewmodel.LoginViewModel

class SignUpActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          val signUpBinding : ActivitySignUpBinding = DataBindingUtil.setContentView(this@SignUpActivity,R.layout.activity_sign_up)
           context = this@SignUpActivity
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        signUpBinding.buttonAcount.setOnClickListener {

           var  strUsername:String =signUpBinding.editEmail.text.toString().trim()
           var  strPassword:String = signUpBinding.editPass.text.toString().trim()

            if (strUsername.isEmpty()) {
                showShortToast("Please enter the Username")
            } else if (strPassword.isEmpty()) {
                showShortToast("Please enter the Password")
            } else {
                loginViewModel.insertData(context, strUsername, strPassword)
                showShortToast("Inserted Successfully")
            }
        }
    }
}