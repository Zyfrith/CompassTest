package com.example.compasstest.presentation.results

import com.example.compasstest.mvi.MviResult

sealed class HomeResult: MviResult {

    object NullHomeStateResult: HomeResult()
    object InitialHomeStateResult: HomeResult()

    sealed class GetData: HomeResult() {
        data class Success(val response: String): GetData()
        object Error: GetData()
    }
}
