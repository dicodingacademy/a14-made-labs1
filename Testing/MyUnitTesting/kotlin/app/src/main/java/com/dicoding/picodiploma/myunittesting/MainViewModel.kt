package com.dicoding.picodiploma.myunittesting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var volume = 0.0

    fun calculateVolume(length: Double, width: Double, height: Double){
        volume = length * width * height
    }
}