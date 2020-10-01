package com.panaceasoft.admotors.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;


/**
 * Created by Panacea-Soft on 8/11/16.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    public String channelId = "PSBuyAndSellChannelId1";
    public  String msg, itemId, buyerUserId, sellerUserId, senderName, senderImagePath;
    public int currentNotiIdPosition , notiId;
    public String userId ,sendUserId;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        /* Important : Please don't change this "message" because if you change this, need to update at PHP.  */

        msg = remoteMessage.getData().get("message");
        itemId = remoteMessage.getData().get("item_id");
        buyerUserId = remoteMessage.getData().get("buyer_id");
        sellerUserId = remoteMessage.getData().get("seller_id");
        senderName = remoteMessage.getData().get("sender_name");
        senderImagePath = remoteMessage.getData().get("sender_profle_photo");

        checkUserId();

        if(msg == null || msg.equals("")) {
            if(remoteMessage.getNotification() != null) {
                msg = remoteMessage.getNotification().getBody();
            }
        }

        showNotification(msg );

    }

    private void checkUserId() {

        if(buyerUserId != null && sellerUserId != null) {
            int result = buyerUserId.compareTo(sellerUserId);
            if (result < 0) {//buyerUserId is less than sellerUserId
                sendUserId = sellerUserId + buyerUserId;
            } else {
                sendUserId = buyerUserId + sellerUserId;
            }
        }

    }


    private void showNotification(String message) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean( Constants.NOTI_EXISTS_TO_SHOW, true);
        editor.apply();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);


        i.putExtra(Constants.NOTI_MSG, message);
        String displayMessage;
        Utils.psLog("Message From Server : " + message);

        if (message == null || message.equals("")) {
            i.putExtra(Constants.NOTI_EXISTS_TO_SHOW, false);
            displayMessage = "Welcome from Admotors";
        } else {
            i.putExtra(Constants.NOTI_EXISTS_TO_SHOW, true);
            displayMessage = message; //"You've received new message.";
        }

        i.putExtra(Constants.SELECTED_LOCATION_ID ,pref.getString(Constants.SELECTED_LOCATION_ID,Constants.EMPTY_STRING));
        i.putExtra(Constants.SELECTED_LOCATION_NAME ,pref.getString(Constants.SELECTED_LOCATION_NAME,Constants.EMPTY_STRING));
        i.putExtra(Constants.LAT ,pref.getString(Constants.LAT,Constants.EMPTY_STRING));
        i.putExtra(Constants.LNG ,pref.getString(Constants.LNG,Constants.EMPTY_STRING));

        i.putExtra(Constants.NOTI_MSG ,msg);
        i.putExtra(Constants.NOTI_ITEM_ID ,itemId);
        i.putExtra(Constants.NOTI_BUYER_ID ,buyerUserId);
        i.putExtra(Constants.NOTI_SELLER_ID ,sellerUserId);
        i.putExtra(Constants.NOTI_SENDER_NAME ,senderName);
        i.putExtra(Constants.NOTI_SENDER_URL ,senderImagePath);


        //i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

//        if( !itemId.isEmpty() ) {
//
//            Intent productIntent = new Intent(this, ItemActivity.class);
//            productIntent.putExtra(Constants.ITEM_ID, itemId);
//            productIntent.putExtra(Constants.HISTORY_FLAG, Constants.ZERO);
//            productIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent intent = PendingIntent.getBroadcast(this, 0, productIntent, 0);
//
//            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, Constants.ZERO)
//                    .setAutoCancel(true)
//                    .setContentTitle(getString(R.string.notification__alert))
//                    .setContentText(message)////
//                    .setSmallIcon(R.drawable.ic_notifications_white)
//                    .setContentIntent(intent);
//
//            NotificationManager manager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//            if (manager2 != null) {
//                manager2.notify(0, builder2.build());
//            }
//        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), Constants.ZERO)
                .setAutoCancel(true)
                .setContentTitle(getString(R.string.notification__alert))
                .setContentText(displayMessage)
                .setSmallIcon(R.drawable.ic_notifications_white)
                .setContentIntent(pendingIntent)
                ;


        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        builder.setDefaults(defaults);
        // Set the content for Notification
        builder.setContentText(displayMessage);
        // Set autocancel
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = this.channelId;
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    getString(R.string.notification__alert),
                    NotificationManager.IMPORTANCE_DEFAULT);
            if(manager != null) {
                manager.createNotificationChannel(channel);
            }
            builder.setChannelId(channelId);
        }

        if (manager != null) {

            if( sendUserId != null) {
                currentNotiIdPosition = pref.getInt(Constants.C_NOTI_ID, 0);
                notiId = pref.getInt(sendUserId, 0);

                if (notiId == 0) {
                    currentNotiIdPosition++;
                    notiId = currentNotiIdPosition;
                    pref.edit().putInt(sendUserId, notiId).apply();
                    pref.edit().putInt(Constants.C_NOTI_ID, currentNotiIdPosition).apply();
                }

                manager.notify(notiId, builder.build());
            }else {
                manager.notify(0, builder.build());
            }

        }


    }


}
