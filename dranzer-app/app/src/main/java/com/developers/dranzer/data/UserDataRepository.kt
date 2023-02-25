package com.developers.dranzer.data

import com.developers.dranzer.data.models.UserData

class UserDataRepository {

    private val singleUserData = UserData(name = IN_MEMORY_SINGLE_USER)

    fun getUserData() = singleUserData

    companion object {
        private const val IN_MEMORY_SINGLE_USER = "Jasmeet"
    }
}