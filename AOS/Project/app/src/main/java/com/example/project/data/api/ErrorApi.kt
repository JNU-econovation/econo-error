package com.example.project.data.api

import com.example.project.MyApplication
import com.example.project.data.remote.CommonResponse
import com.example.project.data.remote.EventRequest
import com.example.project.data.remote.EventResponse
import com.example.project.data.remote.FilterResponse
import com.example.project.data.remote.LoginResponse
import com.example.project.data.remote.MonthEventResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
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
import retrofit2.http.Query
import java.net.CookieManager
import java.text.DateFormat


private const val BASE_URL =
    "https://error.econo-calendar.com:8080/api/"

val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(AppInterceptor())
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



class AppInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()

        val token = MyApplication.prefs.getAToken()
        val requestBuilder = original.newBuilder()

        // 토큰이 있으면 Authorization 헤더에 추가
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}


interface ErrorAPI {


    @GET("calendar/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: Int,
    ): Response<EventResponse>

    @GET("calendar/all")
    suspend fun getAllEvent(
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

    @POST("auth/login/slack")
    suspend fun loginWithSlack(
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri:String,
    ): Response<LoginResponse>


    @GET("filter")
    suspend fun getFilters(

    ): Response<FilterResponse>


//    @GET("calendar/{today}")
//    suspend fun getYearEvent(
//        @Query("period") period: String,
//        @Path("today") today: String,
//    ): YearEventResponse

}

val errorApi: ErrorAPI by lazy {
    retrofit.create(ErrorAPI::class.java)
}