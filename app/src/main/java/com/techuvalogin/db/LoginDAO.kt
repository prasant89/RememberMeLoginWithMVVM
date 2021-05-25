package com.techuvalogin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techuvalogin.model.LoginModel
@Dao
interface LoginDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginModel: LoginModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<LoginModel>

}