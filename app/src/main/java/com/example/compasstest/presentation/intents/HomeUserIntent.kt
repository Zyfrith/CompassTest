package com.example.compasstest.presentation.intents

import com.example.compasstest.mvi.MviUserIntent

sealed class HomeUserIntent: MviUserIntent {
    object NullUserIntent: HomeUserIntent()
    object InitialUserIntent: HomeUserIntent()

    object GetDataIntent: HomeUserIntent()

}