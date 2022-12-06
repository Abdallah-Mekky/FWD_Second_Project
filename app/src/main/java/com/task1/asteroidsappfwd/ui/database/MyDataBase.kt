package com.task1.asteroidsappfwd.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task1.asteroidsappfwd.ui.models.Asteroid
import com.task1.asteroidsappfwd.ui.models.ImageOfDay

@Database(entities = [Asteroid::class, ImageOfDay::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun asteroidDao(): DAO

    companion object {

        var myDataBase: MyDataBase? = null
        var NAME_OF_DATABASE: String = "My DataBase"


        fun initDataBase(context: Context) {

            if (myDataBase == null) {

                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, NAME_OF_DATABASE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): MyDataBase {

            return myDataBase!!
        }
    }
}