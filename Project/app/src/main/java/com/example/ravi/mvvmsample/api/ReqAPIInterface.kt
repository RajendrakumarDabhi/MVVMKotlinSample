package com.example.ravi.mvvmsample.api

import com.example.ravi.mvvmsample.api.Response.ListUsersResponse
import retrofit2.http.GET

interface ReqAPIInterface {

    @GET("/api/users?page=2")
    suspend fun getUsers(): ListUsersResponse



    companion object {
        operator fun invoke(): ReqAPIInterface {
            return ApiClient.retrofit().create(ReqAPIInterface::class.java)

        }
    }
}
