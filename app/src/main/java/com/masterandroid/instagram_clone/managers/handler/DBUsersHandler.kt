package com.masterandroid.instagram_clone.managers.handler

import com.masterandroid.instagram_clone.model.User

interface DBUsersHandler {
    fun onSuccess(users: ArrayList<User>)
    fun onError(e: Exception)
}