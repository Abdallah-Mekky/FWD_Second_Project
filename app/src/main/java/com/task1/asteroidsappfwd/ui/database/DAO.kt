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
     fun insertAll(asteroids: Array<Asteroid>)

    @Query("SELECT * FROM ASTEROID ORDER BY closeApproachDate ASC")
     fun getAllAsteroids(): List<Asteroid>


    @Query("DELETE  FROM Asteroid ")
     fun deleteAllAsteroids()

    @Query("select * from ImageOfDay")
     fun getPictureOfDay(): ImageOfDay

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertPictureOfDay(picture: ImageOfDay)

    @Query("Delete  from ImageOfDay")
     fun deleteAllImages()



    @Query("SELECT * FROM ASTEROID WHERE closeApproachDate == :startDate ORDER By closeApproachDate ASC")
     fun getAsteroidsByDay(startDate: String): List<Asteroid>



    @Query("SELECT * FROM ASTEROID obj WHERE closeApproachDate BETWEEN :startDate AND :endDate order by closeApproachDate Asc")
     fun getAsteroidsByDate(startDate: String, endDate: String): List<Asteroid>
}