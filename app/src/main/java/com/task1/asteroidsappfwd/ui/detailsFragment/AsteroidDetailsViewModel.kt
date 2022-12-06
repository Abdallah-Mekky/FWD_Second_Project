package com.task1.asteroidsappfwd.ui.detailsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AsteroidDetailsViewModel : ViewModel() {

    private val clickedMutableLiveData = MutableLiveData<Boolean>()
    val clicked:LiveData<Boolean> = clickedMutableLiveData

    fun isClicked() {

        clickedMutableLiveData.value = false
    }

    fun isNotClicked() {

        clickedMutableLiveData.value = true
    }
}