package it.flowing.app.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.flowing.app.models.Content
import it.flowing.app.models.Page
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.flowing.it/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlowingApiService {
    @GET("contents")
    fun getContents(
        @Query("categories") categories: String,
        @Query("per_page") perPage: Number,
        @Query("page") page: Number
    ): Deferred<Page<Content>>
}

public enum class Category(val value: String) {
    BLOG("blog")
}

object FlowingApi {
    private val retrofitService: FlowingApiService by lazy {
        retrofit.create(FlowingApiService::class.java)
    }

    fun getContents(vararg categories: Category, perPage: Number, page: Number) =
        retrofitService.getContents(categories.joinToString(separator = ",") { it.value }, perPage, page)
}