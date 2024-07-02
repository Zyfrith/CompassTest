package com.example.compasstest.data.repository.remote

import com.example.compasstest.core.ConstantsResponses.RESPONSE_OK
import com.example.compasstest.core.genericresults.CompassTestResult
import com.example.compasstest.core.readTextAndClose
import com.example.compasstest.data.network.service.ApiServiceImpl
import com.example.compasstest.utils.DownloadUrlInputStream
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: ApiServiceImpl,
    private val downloadUrlInputStream: DownloadUrlInputStream
) : RemoteRepository {

    override suspend fun getData(): CompassTestResult<String> {
        val url = api.buildBaseUrl()
        val inputStream = downloadUrlInputStream.downloadUrl(url)
        return if (inputStream.status == RESPONSE_OK) {
            val responseText = inputStream.entity?.readTextAndClose()
            CompassTestResult(entity = responseText)
        } else {
            CompassTestResult(exception = Exception("Error"))

        }
    }

}