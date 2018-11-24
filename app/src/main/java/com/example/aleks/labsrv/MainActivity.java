package com.example.aleks.labsrv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    TextView log;
    String strLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView5);
        log = findViewById(R.id.log);
    }

    public void calculate(View view) {
        strLog += "Поток ввода: " + Thread.currentThread().getName() + "\n";
        log.setText(strLog);
        MyThread myThread = new MyThread(Double.valueOf(String.valueOf(editText.getText())));
        myThread.start();
    }



    class MyThread extends Thread {
        Double chr = 1.0D;
        Double p = 0.0D;
        Double n = 1.0D;
        Double t;
        String threadName = "";

        MyThread(Double t) {
            super();
            this.t = t;
        }

        Runnable settext = new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(p));
                strLog += "Завершение вычислительного потока \n";
                log.setText(strLog);
            }
        };

        @Override
        public void run() {
            threadName = Thread.currentThread().getName();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    strLog += "Вычислительный поток : " + threadName + "\n";
                    log.setText(strLog);
                }
            });
            while (t < Math.abs(chr)) {
                p += chr;
                chr = ((Math.pow(-1,  n)) * (1 / ((2 * n) + 1)));
                n++;
            }
            p = p * 4;
            runOnUiThread(settext);
        }
    }
}


