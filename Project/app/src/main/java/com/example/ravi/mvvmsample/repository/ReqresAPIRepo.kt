package com.example.ravi.mvvmsample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ravi.mvvmsample.api.ReqAPIInterface
import com.example.ravi.mvvmsample.api.Response.BaseResponse
import com.example.ravi.mvvmsample.api.Response.ListUsersResponse
import com.example.ravi.mvvmsample.api.Response.ResponseStatus
import com.example.ravi.mvvmsample.db.LocalDatabase
import com.example.ravi.mvvmsample.db.dao.UserDao
import com.example.ravi.mvvmsample.models.User
import java.lang.Exception

class ReqresAPIRepo(context: Context) {

    val mReqApi: ReqAPIInterface
    val userDao: UserDao
    val roomDb: LocalDatabase

    init {
        roomDb = LocalDatabase.getDatabase(context)!!
        userDao = roomDb.UserDao()
        mReqApi = ReqAPIInterface.invoke()
    }

    suspend fun getandSaveListUsers(data: MutableLiveData<BaseResponse>) {
        var users: ListUsersResponse? = null
        data.postValue(BaseResponse.getLoadingReponse())
        try {
            users = mReqApi.getUsers()
        } catch (ex: Exception) {
            data.postValue(BaseResponse(ResponseStatus.STATUS_ERROR, ex.toString()))
            return
        }

        try {

            users?.data?.let { userDao.insertAll(it) }
            val stopprogress = BaseResponse.getLoadingReponse()
            stopprogress.data = false
            data.postValue(stopprogress)
        } catch (ex: Exception) {
            data.postValue(BaseResponse(ResponseStatus.STATUS_ERROR, ex.toString()))
        }
    }

    fun getUsers(): LiveData<List<User>> {

        return userDao.getAlphabetizedWords()
    }

    suspend fun getaListUsers(responseLiveData: MutableLiveData<BaseResponse>) {


        var response: BaseResponse = BaseResponse.getLoadingReponse()
        responseLiveData.postValue(response)
        try {
            val users = mReqApi.getUsers()
            response.status = ResponseStatus.STATUS_SUCCESS
            response.data = users

        } catch (ex: Exception) {
            response.status = ResponseStatus.STATUS_ERROR
            response.data = ex.message.toString()

        }
        responseLiveData.postValue(response)

    }


}