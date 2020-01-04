package actiknow.com.motivationalthoughts.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONObject;
import actiknow.com.motivationalthoughts.R;
import actiknow.com.motivationalthoughts.activity.QuotesDetailsActivity;
import actiknow.com.motivationalthoughts.model.NotificationTest;

public class NotificationUtils {
// NotificationTest Type => 1=> new property add, 2=>, 3=>
// NotificationTest Style => 1=> simple_notification, 2=>inbox_style, 3=>big_text_style, 4=>big_picture_style, 5=> custom layout
// NotificationTest Priority => -2=>PRIORITY_MIN, -1=>PRIORITY_LOW, 0=>PRIORITY_DEFAULT, 1=>PRIORITY_HIGH, 2=>PRIORITY_MAX
    
    private static String TAG = NotificationUtils.class.getSimpleName ();
    private Context mContext;
    
    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
    }

    
    public void showNotificationMessage (NotificationTest notification) {
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder (mContext);
        Utils.showLog (Log.DEBUG, TAG, "Data Payload: " + notification.getMessage(), true);
        if (TextUtils.isEmpty (notification.getMessage ()))
            return;
        
        PendingIntent pendingIntent = null;
        Notification notification1;
        NotificationManager mNotifyManager = (NotificationManager) mContext.getSystemService (Context.NOTIFICATION_SERVICE);
        switch (notification.getNotification_type ()) {
            case 1:
                    RemoteViews new_property_expanded = new RemoteViews (mContext.getPackageName (), R.layout.notification_item_property_expanded);
                    RemoteViews new_property_small = new RemoteViews (mContext.getPackageName (), R.layout.notification_item_property_small);

                    JSONObject jsonObject = notification.getPayload ();
                    Intent notificationIntent = new Intent (mContext, QuotesDetailsActivity.class);
                    notificationIntent.putExtra (AppConfigTags.QUOTES, notification.getMessage());
                    pendingIntent = PendingIntent.getActivity (mContext, 0, notificationIntent, 0);
                new_property_expanded.setTextViewText(R.id.tvQuotes,notification.getMessage());
                mBuilder.setCustomBigContentView (new_property_expanded)
                      //  .setCustomContentView (new_property_small)
                        .setAutoCancel (true)
                        .setSmallIcon (R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setStyle (new NotificationCompat.BigPictureStyle ());

                notification1 = mBuilder.build ();
                notification1.contentIntent = pendingIntent;
                notification1.flags |= Notification.FLAG_AUTO_CANCEL; //Do not clear  the notification
                notification1.defaults |= Notification.DEFAULT_LIGHTS; // LED
                notification1.defaults |= Notification.DEFAULT_VIBRATE;//Vibration
                notification1.defaults |= Notification.DEFAULT_SOUND; // Sound
                mNotifyManager.notify (notification.getNotification_type (), notification1);
                break;


            case 2:
                Intent intent = new Intent(mContext, QuotesDetailsActivity.class);
                intent.putExtra (AppConfigTags.QUOTES, notification.getMessage());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(mContext, 1410,
                        intent, PendingIntent.FLAG_ONE_SHOT);
                NotificationCompat.Builder notificationBuilder = new
                        NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(notification.getTitle ())
                        .setContentText(notification.getMessage())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent1);

                NotificationManager notificationManager =
                        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1410, notificationBuilder.build());

        }
    }
}