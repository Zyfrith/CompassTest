package com.example.compasstest.data.repository

import com.example.compasstest.core.genericresults.CompassTestResult
import com.example.compasstest.data.model.api.ImageModel
import com.example.compasstest.data.repository.remote.RemoteRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val remoteBackRepository: RemoteRepositoryImpl
) {

    fun getData(): Flow<CompassTestResult<String>> {
        return flow {
            emit(remoteBackRepository.getData())
        }
    }
}