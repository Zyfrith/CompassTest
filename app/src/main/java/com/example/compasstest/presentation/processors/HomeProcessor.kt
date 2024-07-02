package com.example.compasstest.presentation.processors

import com.example.compasstest.domain.usecases.GetDataUseCase
import com.example.compasstest.domain.usecases.InitialHomeStateUseCase
import com.example.compasstest.domain.usecases.NullHomeStateUseCase
import com.example.compasstest.presentation.actions.HomeAction
import com.example.compasstest.presentation.results.HomeResult
import javax.inject.Inject

class HomeProcessor @Inject constructor(
    private val isNullHomeStateUseCase: NullHomeStateUseCase,
    private val isInitialHomeStateUseCase: InitialHomeStateUseCase,
    private val getDataUseCase: GetDataUseCase
) {

    suspend fun dispatchAction(action: HomeAction): HomeResult {
        return when (action) {
            is HomeAction.NullHomeAction -> isNullHomeState()
            is HomeAction.InitialHomeAction -> isInitialHomeState()
            is HomeAction.GetDataHomeAction -> getData()
        }
    }

    private fun isNullHomeState(): HomeResult {
        return isNullHomeStateUseCase.isNullHomeState()
    }

    private fun isInitialHomeState(): HomeResult {
        return isInitialHomeStateUseCase.isInitialHomeState()
    }

    private suspend fun getData(): HomeResult {
        val result = getDataUseCase.getData()
        return if (!result.entity.isNullOrEmpty()) {
            HomeResult.GetData.Success(response = result.entity!!)
        } else {
            HomeResult.GetData.Error
        }
    }

}