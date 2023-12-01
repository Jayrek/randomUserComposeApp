package com.jrektabasa.randomuser.network

import com.jrektabasa.randomuser.model.RandomUserResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserInterface {
    @GET("/api/")
    suspend fun getRandomUserList(
        @Query("results") result: Int
    ): RandomUserResult
}