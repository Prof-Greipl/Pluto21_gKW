package de.hawlandshut.pluto21_gkw;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "xx MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "Message received : " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Received Firebase Notification : "
                    + "\nTitle : " + remoteMessage.getNotification().getTitle()
                    + "\nBody  : " + remoteMessage.getNotification().getBody());
        }
    }
}
