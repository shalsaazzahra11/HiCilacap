package org.d3ifcool.hicilacap.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3ifcool.hicilacap.model.Data
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/hicilacap.appspot.com/o/"
enum class ApiStatus { LOADING, SUCCESS, FAILED }

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

val service: ApiService by lazy { retrofit.create(ApiService::class.java) }

interface ApiService {
    @GET("fabulouss.json?alt=media&token=36ddd7bf-62da-4a9a-bc1e-cd68ec713f4b")
    suspend fun getData(): Data
}
