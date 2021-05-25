package com.techuvalogin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.techuvalogin.model.LoginModel
import com.techuvalogin.repository.LoginRepository

class LoginViewModel:ViewModel() {

    var liveDataLogin: LiveData<LoginModel>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<LoginModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

}