package com.example.completenavigation.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Photo(
    val url: String,
) : Parcelable
