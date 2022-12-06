package com.task1.asteroidsappfwd.ui.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageOfDay(

    @ColumnInfo
    val media_type: String? = null,
    @PrimaryKey
    @ColumnInfo
    val title: String,
    @ColumnInfo
    var url: String? = null
)