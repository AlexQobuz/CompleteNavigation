package com.example.completenavigation.user

import androidx.lifecycle.ViewModel

/**
 * Je définis ici les données
 * que je veux conserver et gérer dans mon ViewModel
 */

class UsersViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userListLiveData = userRepository.getAllUsers()

    constructor() : this(UserRepository())

}