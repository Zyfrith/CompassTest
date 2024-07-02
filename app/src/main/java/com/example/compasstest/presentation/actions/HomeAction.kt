package com.example.compasstest.presentation.actions

import com.example.compasstest.mvi.MviAction

sealed class HomeAction: MviAction {

    object NullHomeAction: HomeAction()
    object InitialHomeAction: HomeAction()
    object GetDataHomeAction: HomeAction()

}