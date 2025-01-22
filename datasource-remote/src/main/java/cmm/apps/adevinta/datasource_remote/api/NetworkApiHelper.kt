package cmm.apps.adevinta.datasource_remote.api

import cmm.apps.adevinta.datasource_remote.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkApiHelper {

    companion object {
        fun adevintaApiBaseUrl(): String = BuildConfig.ADEVINTA_API_BASE_URL
    }

    fun <T> provideApi(
        baseUrl: String,
        clazz: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(clazz)
    }

}
