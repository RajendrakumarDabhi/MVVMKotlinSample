package com.example.ravi.mvvmsample.api.Response

import com.example.ravi.mvvmsample.models.User
import com.squareup.moshi.Json

data class ListUsersResponse(@Json(name = "per_page")
                             val perPage: Int = 0,
                             @Json(name="total")
                             val total: Int = 0,
                             @Json(name = "data")
                             val data: List<User>?,
                             @Json(name = "page")
                             val page: Int = 0,
                             @Json(name="total_pages")
                             val totalPages: Int = 0)


