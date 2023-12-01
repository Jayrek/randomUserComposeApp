package com.jrektabasa.randomuser.data.repository.get_user_by_count

import com.jrektabasa.randomuser.model.RandomUserResult

interface GetUserByCountRepository {

    suspend fun getRandomUserByCount(
        result: Int
    ): RandomUserResult
}