package com.ostin.tradume.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ostin.tradume.api.models.ResponseDTO
import com.ostin.tradume.api.models.WordDTO
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
interface TradumeApi {
    @GET("words")
    suspend fun words(): Result<ResponseDTO<WordDTO>>
}

fun TradumeApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): TradumeApi {
    return retrofit(
        baseUrl = baseUrl,
        okHttpClient = okHttpClient,
        json = json
    ).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(httpLoggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}
