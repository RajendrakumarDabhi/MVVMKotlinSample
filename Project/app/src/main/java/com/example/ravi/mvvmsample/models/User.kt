package com.example.ravi.mvvmsample.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "User")
data class User(@Json(name = "last_name")
                val last_name: String = "",
                @Json(name = "id")
                @PrimaryKey
                val id: Int = 0,
                @Json(name = "avatar")
                val avatar: String = "",
                @Json(name = "first_name")
                val first_name: String = "",
                @Json(name = "email")
                val email: String = "")


