package com.masterandroid.instagram_clone.managers.handler

import com.masterandroid.instagram_clone.model.Post
import java.lang.Exception

interface DBPostHandler {
    fun onSuccess(post: Post)
    fun onError(e: Exception)
}