package com.example.ravi.mvvmsample.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ravi.mvvmsample.api.Response.BaseResponse
import com.example.ravi.mvvmsample.models.User
import com.example.ravi.mvvmsample.repository.ReqresAPIRepo
import com.example.ravi.mvvmsample.ui.BaseClasses.BaseViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : BaseViewModel(application) {

    val data: MutableLiveData<BaseResponse>
    val mRepository: ReqresAPIRepo
    val mListUsers: MutableLiveData<List<User>>

    init {
        data = MutableLiveData()
        mRepository = ReqresAPIRepo(application)
        mListUsers = MutableLiveData()
    }

    fun getUsers() {
        GlobalScope.launch {
            mRepository.getaListUsers(data)

        }

    }

    fun getandSaveListUsers() {
        data.value=BaseResponse.getLoadingReponse()
        GlobalScope.launch {
            mRepository.getandSaveListUsers(data)

        }

    }

    fun getListUsers(): LiveData<List<User>> {
        return mRepository.getUsers()
    }

    fun getListUsersLiveData(): MutableLiveData<BaseResponse> {
        return data
    }
}