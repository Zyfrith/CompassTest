package com.example.compasstest.mvi

interface MviUi<TUserIntent: MviUserIntent, in TUiState: MviUiState> {
    fun renderUiStates(uiState: TUiState)
}