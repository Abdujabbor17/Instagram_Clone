package com.masterandroid.instagram_clone.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.masterandroid.instagram_clone.utils.Logger

class FCMService:FirebaseMessagingService() {
    val TAG = FCMService::class.java.simpleName

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Logger.i(TAG,"Refreshed token :: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Logger.i(TAG, "Message :: $message")
    }

}