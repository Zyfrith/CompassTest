package com.example.compasstest.data.repository.remote

import com.example.compasstest.core.genericresults.CompassTestResult
import com.example.compasstest.data.model.api.ImageModel

interface RemoteRepository {
    suspend fun getData(): CompassTestResult<String>
}