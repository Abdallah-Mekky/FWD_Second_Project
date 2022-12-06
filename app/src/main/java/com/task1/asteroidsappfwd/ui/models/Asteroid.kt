package com.task1.asteroidsappfwd.ui.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Asteroid constructor(

    @PrimaryKey()
    @ColumnInfo
    val id: Long,

    @ColumnInfo
    val codename: String,
    @ColumnInfo
    val closeApproachDate: String,
    @ColumnInfo
    val absoluteMagnitude: Double,
    @ColumnInfo
    val estimatedDiameter: Double,
    @ColumnInfo
    val relativeVelocity: Double,
    @ColumnInfo
    val distanceFromEarth: Double,
    @ColumnInfo
    val isPotentiallyHazardous: Boolean
) : Parcelable
