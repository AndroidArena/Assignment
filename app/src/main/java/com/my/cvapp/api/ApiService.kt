package com.my.cvapp.api

import com.my.cvapp.userprofile.model.UserInfo
import retrofit2.http.GET

interface ApiService {
    @GET(".")
    suspend fun getUser(): UserInfo
}