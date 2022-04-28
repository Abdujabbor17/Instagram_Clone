package com.masterandroid.instagram_clone.managers.handler

import com.masterandroid.instagram_clone.model.User

interface DBUserHandler {
    fun onSuccess(user: User?=null)
    fun onError(e:Exception)
}