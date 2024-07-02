package com.example.compasstest.domain.usecases

import com.example.compasstest.core.genericresults.CompassTestResult
import com.example.compasstest.data.model.api.ImageModel
import com.example.compasstest.data.repository.CommonDataRepository
import javax.inject.Inject

class GetDataUseCase@Inject constructor(
    private val commonDataRepository: CommonDataRepository
) {
    suspend fun getData(): CompassTestResult<String> {
        var userData: CompassTestResult<String> = CompassTestResult()
        commonDataRepository.getData().collect{
            userData = it
        }
        return userData
    }
}