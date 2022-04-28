package com.masterandroid.instagram_clone.model

data class FirebaseResponse(
    val canonical_ids: Int,
    val failure: Int,
    val multicast_id: Long,
    val results: List<Result>,
    val success: Int
)

data class Result(
    val message_id: String
)