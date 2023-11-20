package com.jrektabasa.randomuser.data.repository.get_user_by_count.impl

import android.util.Log
import com.jrektabasa.randomuser.data.repository.get_user_by_count.GetUserByCountRepository
import com.jrektabasa.randomuser.model.RandomUserResult
import com.jrektabasa.randomuser.network.RandomUserInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserByCountRepositoryImpl @Inject constructor(
    private val api: RandomUserInterface
) : GetUserByCountRepository {
    override suspend fun getRandomUserByCount(result: Int): RandomUserResult {
        try {
            return api.getRandomUserList(result)
        } catch (e: Exception) {
            Log.d("repositoryImpl", "getRandomUserByCount: ${e.message}")
            throw Exception(e.message)
        }
    }
}