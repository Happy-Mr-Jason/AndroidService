package com.example.vibrateex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rGroup;
    RadioButton rdo1, rdo2;
    Button btnOK;
    TextView tvResult;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        rdo1 = (RadioButton) findViewById(R.id.rdo1);
        rdo2 = (RadioButton) findViewById(R.id.rdo2);
        btnOK = (Button) findViewById(R.id.btnOK);
        tvResult = (TextView) findViewById(R.id.tvResult);
        mp = MediaPlayer.create(this,R.raw.tscerr00);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGroup.getCheckedRadioButtonId()) {
                    case R.id.rdo1:
                        //Vibrator를 구동해보자 Vibrator_Service를 가져와서 vibrator사용 준비를 한다.
                        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                        //Vibrate를 실행한다. int= 1000 1초동안 Vibrating
//                        vibrator.vibrate(1000);
                        //Long Array 주기적으로 Vibrating (repeat : 0 = loop, -1 = 1time)
                        vibrator.vibrate(new long[]{500,1000,400,2000}, -1);
                        // if repeat = 0 (Loop) 인 경우 vibrator to stop
//                        vibrator.cancel();
                        tvResult.setText("틀렸습니다.");
                        break;
                    case R.id.rdo2:
                        //RintonManager가 가지고 있는 소리(내장된 소리)를 사용하여 소리가 나오도록 한다.
                        //TYPE_NOTIFICATION 말고 다양한 TYPE들이 있다.
                        /*Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),noti);
                        ringtone.play();*/
                        mp.start();
                        tvResult.setText("정답입니다. \n 이유는 별들에게 물어봐");
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "선택해줘", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
