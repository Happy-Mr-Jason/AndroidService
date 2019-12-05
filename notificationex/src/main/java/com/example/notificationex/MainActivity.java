package com.example.notificationex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNotification;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotification = (Button) findViewById(R.id.btnNotification);

        //화면에 Notification 알림을 가져온다. (need just NotificationManager under the Oreo version)
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // In upper Oreo version, You have to make a channels for the Notification priority.
        if (Build.VERSION.SDK_INT >= 26) {
            //Create NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel("noti", "notiChannel", NotificationManager.IMPORTANCE_DEFAULT);
            // Channel 의 설명문
            notificationChannel.setDescription("채널");
            // light를 켜준다.
            notificationChannel.enableLights(true);
            // Light 색상을 지정한다.
            notificationChannel.setLightColor(Color.GREEN);
            // 알림이 발생했을때 진동울림 지정한다.
            notificationChannel.enableVibration(true);
            // 알림 진동 패턴 설정
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            // 개인 용도로 사용함을 지정
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            //Create NotificationChannel
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //Notification에 사요할 intent
        //PendingIntent pendingIntent = PendingIntent.getActivity(호출한 장소, 0,new Intent(getApplicationContext(),보여줄 화면),PendingIntent.FLAG_UPDATE_CURRENT);
        final PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification_largeicon);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, "noti")
                        .setSmallIcon(R.drawable.ic_notification_icon)          //statusbar에 표시되는 작은 아이콘
                        .setContentTitle("공지사항")
                        .setContentText("내일은 노티활용편입니다.")
                        .setLargeIcon(largeIcon)                                //statusbar를 내리면 나타나는 큰 아이콘
                        .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)              // 알림이 떴을때 발생하는 액션
                        .setAutoCancel(true)                                    // 터치하면 사라지도록 한다.
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)       //중요도 설정
                        .setContentIntent(pendingIntent);                                     // noti를 클릭했을때 실행 할 Activity
                notificationManager.notify(0, mBuilder.build());
            }
        });
    }
}
