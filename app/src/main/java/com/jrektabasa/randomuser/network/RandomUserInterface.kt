package com.jrektabasa.randomuser.network

import com.jrektabasa.randomuser.model.RandomUserResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserInterface {
    @GET("/api/")
    suspend fun getRandomUserList(
        @Query("page") page: Int,
        @Query("results") result: Int,
        @Query("gender") gender: String,
        @Query("nat") nat: List<String>,
    ): RandomUserResult
}

//https://randomuser.me/api/?page=3&results=2&gender=female&nat=us,dk,fr,gb