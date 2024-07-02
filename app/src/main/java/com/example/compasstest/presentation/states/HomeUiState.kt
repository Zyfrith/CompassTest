package com.example.compasstest.presentation.states

import com.example.compasstest.mvi.MviUiState

sealed class HomeUiState: MviUiState {

    object NullUiState: HomeUiState()

    object InitialUiState: HomeUiState()

}