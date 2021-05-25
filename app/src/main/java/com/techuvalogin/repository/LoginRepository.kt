package com.techuvalogin.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.techuvalogin.db.LoginDatabase
import com.techuvalogin.model.LoginModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {

        var loginDatabase: LoginDatabase? = null

        var loginModel: LiveData<LoginModel>? = null

        fun initializeDB(context: Context) : LoginDatabase {
            return LoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = LoginModel(username, password)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<LoginModel>? {

            loginDatabase = initializeDB(context)

            loginModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginModel
        }

    }

}