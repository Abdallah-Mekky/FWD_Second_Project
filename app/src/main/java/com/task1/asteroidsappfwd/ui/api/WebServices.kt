package com.task1.asteroidsappfwd.ui.api

import com.task1.asteroidsappfwd.ui.models.ImageOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidList(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String


    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String
    ): ImageOfDay
}