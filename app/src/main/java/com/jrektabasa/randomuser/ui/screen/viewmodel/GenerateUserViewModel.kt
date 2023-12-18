package com.jrektabasa.randomuser.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.jrektabasa.randomuser.data.GeneratedUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GenerateUserViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(GeneratedUserUiState())
    val uiState: StateFlow<GeneratedUserUiState> = _uiState.asStateFlow()

    fun setUserCount(count: Int) {
        _uiState.update { it.copy(userGenerateCount = count) }
    }

    fun setUserNationalities(natList: List<String>) {
        _uiState.update { it.copy(nationalities = natList) }
    }

}
