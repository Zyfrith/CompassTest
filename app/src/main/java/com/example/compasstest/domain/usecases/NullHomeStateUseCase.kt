package com.example.compasstest.domain.usecases

import com.example.compasstest.presentation.results.HomeResult
import javax.inject.Inject

class NullHomeStateUseCase @Inject constructor() {
    fun isNullHomeState(): HomeResult.NullHomeStateResult {
        return HomeResult.NullHomeStateResult
    }
}