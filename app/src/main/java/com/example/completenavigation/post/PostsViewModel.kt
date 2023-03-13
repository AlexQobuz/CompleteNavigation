package com.example.completenavigation.post

import androidx.lifecycle.ViewModel

class PostsViewModel(private val postRepository: PostRepository) : ViewModel() {

    val postListLiveData = postRepository.getAllPosts()

    constructor() : this(PostRepository())

}