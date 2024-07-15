package com.example.project.data.api

import com.example.project.data.remote.CommonResponse
import com.example.project.data.remote.EventRequest
import com.example.project.data.remote.EventResponse
import com.example.project.data.remote.MonthEventResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.net.CookieManager
import java.text.DateFormat


private const val BASE_URL =
    "https://error.econo-calendar.com:8080/api/"

val client: OkHttpClient = OkHttpClient.Builder()
//    .addInterceptor(AppInterceptor())
    .cookieJar(JavaNetCookieJar(CookieManager()))
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

val gson: Gson = GsonBuilder().serializeNulls().setDateFormat(DateFormat.LONG).create()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()




//class AppInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
////        val accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MSwibWVtYmVyUm9sZSI6IltST0xFX1VTRVJdIiwiaWF0IjoxNzA1MTMzMjI1LCJleHAiOjE3MDcyOTMyMjV9.UVd3n1O508y54CC0dvVry30lgS3Zxoo7UynyNAggTd0"
////        val refreshToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MSwibWVtYmVyUm9sZSI6IltST0xFX1VTRVJdIiwiaWF0IjoxNzA1MTMzMjI1LCJleHAiOjE3MDcyOTMyMjV9.UVd3n1O508y54CC0dvVry30lgS3Zxoo7UynyNAggTd0"
//        val original = chain.request()
//
//        if (original.url.encodedPath.equals("/v1/members", true)
//            && original.method.equals("POST", true) ) {
//            chain.proceed(original)
//        } else {
//            chain.proceed(original.newBuilder().apply {
//                addHeader("Authorization", "Bearer ${MyApplication.prefs.getAToken()}")
//            }.build())
//        }
//    }
//}


interface ErrorApi {


    @GET("calendar/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: Int,
    ): Response<EventResponse>

    @GET("calendar/all/{today}")
    suspend fun getAllEvent(
        @Path("today") today: String,
    ): Response<MonthEventResponse>


    @POST("calendar")
    suspend fun createEvent(
        @Body event: EventRequest
    ): Response<CommonResponse>

    @DELETE("calendar/{eventId}")
    suspend fun deleteEvent(
        @Path("eventId") eventId: Int
    ): Response<CommonResponse>

    @PUT("calendar/{eventId}")
    suspend fun updateEvent(
        @Path("eventId") eventId: Int,
        @Body event: EventRequest
    ): Response<CommonResponse>

//    @GET("calendar/{today}")
//    suspend fun getYearEvent(
//        @Query("period") period: String,
//        @Path("today") today: String,
//    ): YearEventResponse

}

val errorApi: ErrorApi by lazy {
    retrofit.create(ErrorApi::class.java)
}