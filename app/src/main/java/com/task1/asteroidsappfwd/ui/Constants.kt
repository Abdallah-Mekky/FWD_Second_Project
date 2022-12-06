package com.task1.asteroidsappfwd.ui

import com.task1.asteroidsappfwd.BuildConfig


object Constants {

    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = BuildConfig.myApiKey;
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 5
}