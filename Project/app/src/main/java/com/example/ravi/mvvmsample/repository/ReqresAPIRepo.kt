package com.example.ravi.mvvmsample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ravi.mvvmsample.api.ReqAPIInterface
import com.example.ravi.mvvmsample.db.LocalDatabase
import com.example.ravi.mvvmsample.db.dao.UserDao
import com.example.ravi.mvvmsample.models.User

class ReqresAPIRepo(context: Context) {

    val mReqApi: ReqAPIInterface
    val userDao: UserDao
    val roomDb: LocalDatabase

    init {
        roomDb = LocalDatabase.getDatabase(context)!!
        userDao = roomDb.UserDao()
        mReqApi = ReqAPIInterface.invoke()
    }

    suspend fun getandSaveListUsers() {
        val users = mReqApi.getUsers()
        users.data?.let { userDao.insertAll(it) }
    }
     fun getUsers(): LiveData<List<User>> {

       return userDao.getAlphabetizedWords()
    }

}