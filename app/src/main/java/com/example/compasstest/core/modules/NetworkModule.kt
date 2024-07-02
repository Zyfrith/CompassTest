package com.example.compasstest.core.modules

import android.net.Uri
import com.example.compasstest.core.ConstantsUrls.URL_AUTHORITY
import com.example.compasstest.core.ConstantsUrls.URL_HTTPS_SCHEME
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule @Inject constructor() {

    @Singleton
    fun buildBaseUrl(): Uri.Builder {
        val builder = Uri.Builder()
        builder.scheme(URL_HTTPS_SCHEME)
            .authority(URL_AUTHORITY)
        return builder
    }

}