package com.masterandroid.instagram_clone.networking

import com.masterandroid.instagram_clone.model.FirebaseRequest
import com.masterandroid.instagram_clone.model.FirebaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

@JvmSuppressWildcards
interface ApiService {


    @Headers("Authorization:$ACCESS_KEY")
    @POST("send")
    fun sendNotification(@Body firebaseRequest: FirebaseRequest): Call<FirebaseResponse>

    companion object {
        const val ACCESS_KEY = "AAAAUTOiEMc:APA91bFJ93-NRkaLVxfsvk6Oc1qhQhzjON2HKU4RE-Iy27bJnjqIt1MvBzwV51H8gcHIQ2UHRjvroYRHOKKt5TsQfYsfne7grxqSspIC1EEZUFOdPY1E1stlS2pmT1ywcuEFNpMrSvak"
    }
}