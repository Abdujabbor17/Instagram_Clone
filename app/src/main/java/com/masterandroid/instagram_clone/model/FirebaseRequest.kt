package com.masterandroid.instagram_clone.model

data class FirebaseRequest(
    val notification: Notification,
    val registration_ids: List<String>
)

data class Notification(
    val body: String,
    val title: String
)