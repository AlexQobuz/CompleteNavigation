package com.example.completenavigation.home

import androidx.lifecycle.ViewModel

class PhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    val photoListLiveData = photoRepository.getAllPhotos()

    constructor() : this(PhotoRepository())

}