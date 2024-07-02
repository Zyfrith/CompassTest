package com.example.compasstest.mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntent(userIntent: TUserIntent)

    fun uiStates(): StateFlow<TUiState>

    fun setUiState(state: TUiState)

    val uiIntents: Channel<TUserIntent>

    val uiStates: MutableStateFlow<TUiState>

}