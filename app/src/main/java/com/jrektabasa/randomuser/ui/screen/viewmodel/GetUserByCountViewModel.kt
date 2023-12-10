package com.jrektabasa.randomuser.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.randomuser.data.repository.get_user_by_count.GetUserByCountRepository
import com.jrektabasa.randomuser.model.RandomUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetUserByCountViewModel @Inject constructor(
    private val repository: GetUserByCountRepository
) : ViewModel() {

    private val _user = MutableStateFlow<RandomUserResult?>(null)
    val user = _user.asStateFlow()

    fun getUserByCount(count: Int) {
        viewModelScope.launch {
            val response = repository.getRandomUserByCount(count)
            _user.value = response
        }
    }
}