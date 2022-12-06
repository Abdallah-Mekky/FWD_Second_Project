package com.task1.asteroidsappfwd.ui.mainAsteroidsFragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task1.asteroidsappfwd.ui.*
import com.task1.asteroidsappfwd.ui.api.*
import com.task1.asteroidsappfwd.ui.database.MyDataBase
import com.task1.asteroidsappfwd.ui.models.Asteroid
import com.task1.asteroidsappfwd.ui.models.ImageOfDay
import kotlinx.coroutines.launch
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.N)
class MainAsteroidsViewModel : ViewModel() {

    private var currentImageMutableLiveData = MutableLiveData<ImageOfDay>()
    var currentImage:LiveData<ImageOfDay> = currentImageMutableLiveData

    var asteroidsListMutableLiveData = MutableLiveData<List<Asteroid>>()
    var asteroidsList:LiveData<List<Asteroid>> = asteroidsListMutableLiveData

    var progressAsteroidsMutableLiveData = MutableLiveData<Boolean>()
    var progressAsteroids:LiveData<Boolean> = progressAsteroidsMutableLiveData

    init {

        getImageOfDay()
        getAllAsteroids()
    }


    private fun getImageOfDay() {

        viewModelScope.launch {


            try {

                val result = ApiManager.getApis().getImageOfDay(Constants.API_KEY)


                MyDataBase.getInstance().asteroidDao().deleteAllImages()
                MyDataBase.getInstance().asteroidDao().insertPictureOfDay(result)


                currentImageMutableLiveData.value = MyDataBase.getInstance().asteroidDao().getPictureOfDay()


            } catch (ex: Exception) {

                currentImageMutableLiveData.value = MyDataBase.getInstance().asteroidDao().getPictureOfDay()

            }


        }

    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun getAllAsteroids() {

        viewModelScope.launch {
            progressAsteroidsMutableLiveData.value = true
            try {

                val result = ApiManager.getApis().getAsteroidList(
                    startDate = getStartDate(),
                    endDate = getEndDate(), apiKey = Constants.API_KEY
                )

                val data = parseAsteroidsJsonResult(
                    JSONObject(result)
                )

                val finalList = data.asAsteroidEntities()
                progressAsteroidsMutableLiveData.value = false

                MyDataBase.getInstance().asteroidDao().deleteAllAsteroids()
                MyDataBase.getInstance().asteroidDao().insertAll(finalList)

                asteroidsListMutableLiveData.value = MyDataBase.getInstance().asteroidDao().getAllAsteroids()

            } catch (ex: Exception) {

                asteroidsListMutableLiveData.value = MyDataBase.getInstance().asteroidDao().getAllAsteroids()
                progressAsteroidsMutableLiveData.value = false
            }
        }
    }


    fun getAsteroidsByDay(currentDay: String) {

        viewModelScope.launch {

            try {
                asteroidsListMutableLiveData.value = MyDataBase.getInstance().asteroidDao().getAsteroidsByDay(currentDay)

            }catch (ex:Exception){

                throw ex
            }


        }

    }

    fun getAsteroidByDate(startDate: String, endDate: String) {

        viewModelScope.launch {

            try {
                asteroidsListMutableLiveData.value =
                    MyDataBase.getInstance().asteroidDao().getAsteroidsByDate(startDate, endDate)

            }catch (ex:Exception){
                throw ex
            }
        }
    }

}