package com.dicoding.picodiploma.myunittesting

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Assert.assertEquals

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun testVolumeWithIntegerInput() {
        mainViewModel.calculateVolume(2.0, 8.0, 1.0)
        val volume = mainViewModel.volume
        assertEquals(16.0, volume, 0.0001)
    }

    @Test
    fun testVolumeWithDoubleInput() {
        mainViewModel.calculateVolume(2.3, 8.1, 2.9)
        val volume = mainViewModel.volume
        assertEquals(54.026999999999994, volume, 0.0001)
    }

    @Test
    fun testVolumeWithZeroInput() {
        mainViewModel.calculateVolume(0.0, 0.0, 0.0)
        val volume = mainViewModel.volume
        assertEquals(0.0, volume, 0.0001)
    }
    
    @Test
    fun calculateVolume() {
        val width = 1.0
        val length = 2.0
        val height = 3.0
        mainViewModel.calculateVolume(width, height, length)
        val volume = mainViewModel.volume
        assertEquals(6.0, volume, 0.0001)
    }
}