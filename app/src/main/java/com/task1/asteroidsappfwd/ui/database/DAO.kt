package com.task1.asteroidsappfwd.ui.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task1.asteroidsappfwd.ui.models.Asteroid
import com.task1.asteroidsappfwd.ui.models.ImageOfDay

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: Array<Asteroid>)

    @Query("SELECT * FROM ASTEROID ORDER BY closeApproachDate ASC")
    suspend fun getAllAsteroids(): List<Asteroid>


    @Query("DELETE  FROM Asteroid ")
    suspend fun deleteAllAsteroids()

    @Query("select * from ImageOfDay")
    suspend fun getPictureOfDay(): ImageOfDay

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay(picture: ImageOfDay)

    @Query("Delete  from ImageOfDay")
    suspend fun deleteAllImages()


    @Query("SELECT * FROM ASTEROID WHERE closeApproachDate = :startDate ORDER BY closeApproachDate ASC")
    suspend fun getAsteroidsByDay(startDate: String): List<Asteroid>

    @Query("SELECT * FROM ASTEROID WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    suspend fun getAsteroidsByDate(startDate: String, endDate: String): List<Asteroid>
}