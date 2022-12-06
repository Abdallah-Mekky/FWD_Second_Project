package com.task1.asteroidsappfwd.ui.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AsteroidDetailsViewModel : ViewModel() {

    val clicked = MutableLiveData<Boolean>()

    fun isClicked() {

        clicked.value = false
    }

    fun isNotClicked() {

        clicked.value = true
    }
}