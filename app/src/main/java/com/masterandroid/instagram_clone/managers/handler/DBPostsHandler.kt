package com.masterandroid.instagram_clone.managers.handler


import com.masterandroid.instagram_clone.model.Post
import java.lang.Exception

interface DBPostsHandler {
    fun onSuccess(posts: ArrayList<Post>)
    fun onError(e: Exception)
}