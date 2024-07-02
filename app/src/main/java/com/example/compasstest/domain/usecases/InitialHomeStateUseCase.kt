package com.example.compasstest.domain.usecases

import com.example.compasstest.presentation.results.HomeResult
import javax.inject.Inject

class InitialHomeStateUseCase @Inject constructor() {
    fun isInitialHomeState(): HomeResult.InitialHomeStateResult {
        return HomeResult.InitialHomeStateResult
    }
}