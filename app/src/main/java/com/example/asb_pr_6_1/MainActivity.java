package com.example.asb_pr_6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Chronometer cm1;
    Button btnStart, btnEnd;
    LinearLayout l1;

    FrameLayout fl1;
    RadioGroup rg1;
    RadioButton rbtn1, rbtn2;

    CalendarView cv1;

    TimePicker tp1;

    TextView tv2;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("예약~");

        cm1 = (Chronometer)findViewById(R.id.cm1);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        l1 = (LinearLayout) findViewById(R.id.l1);
        fl1 = (FrameLayout)findViewById(R.id.fl1);

        rg1 = (RadioGroup)findViewById(R.id.rg1);

        rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton) findViewById(R.id.rbtn2);

        cv1 = (CalendarView) findViewById(R.id.cv1);
        tp1 = (TimePicker)findViewById(R.id.tp1);

        btnEnd = (Button)findViewById(R.id.btnEnd);
        tv2 = (TextView)findViewById(R.id.tv2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag ==1){
                    flag = 0;
                    tv2.setText("");
                    btnEnd.setText("예약하기");
                }

                l1.setVisibility(View.VISIBLE);
                fl1.setVisibility(View.VISIBLE);

                cm1.setBase(SystemClock.elapsedRealtime());
                cm1.start();
            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbtn1){
                    Log.d("************", i+"");
                    Log.d("************", R.id.rbtn1+"");
                    cv1.setVisibility(View.VISIBLE);
                    tp1.setVisibility(View.INVISIBLE);

                }else if(i == R.id.rbtn2){
                    Log.d("************", i+"");
                    Log.d("************", R.id.rbtn2+"");
                    cv1.setVisibility(View.INVISIBLE);
                    tp1.setVisibility(View.VISIBLE);
                }
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag ==1){
                    Toast.makeText(getApplicationContext(), "이미 예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    cm1.stop();
                    flag = 1;

                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
                    String selectedDate1 = sdf1.format(new Date(cv1.getDate()));

                    int hour = tp1.getCurrentHour();
                    int min = tp1.getCurrentMinute();
                    btnEnd.setText("예약완료");
                    tv2.setText(selectedDate1+" "+hour+":"+min+" 예악이 완료되었습니다.");
                }
            }
        });

    }
}