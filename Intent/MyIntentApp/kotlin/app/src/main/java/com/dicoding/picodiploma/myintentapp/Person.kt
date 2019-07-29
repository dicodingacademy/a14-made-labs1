package com.dicoding.picodiploma.myintentapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by sidiqpermana on 9/11/16.
 */
@Parcelize
data class Person(
        val name:String,
        val age: Int,
        val email: String,
        val city: String
) : Parcelable

