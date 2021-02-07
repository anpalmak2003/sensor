package com.example.service;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mgr;
    private Sensor light;
    ImageView smile; ImageView skrim;
    MediaPlayer player;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         smile = findViewById(R.id.imageView);
        skrim = findViewById(R.id.imageView1);
        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        light = mgr.getDefaultSensor(Sensor.TYPE_LIGHT);

        player = MediaPlayer.create(this, R.raw.music); player.setLooping(true);
        player.setVolume(100,100);
    }

    @Override
    protected void onResume() {
        mgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, light);
        super.onPause();
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {

        if(event.values[0] <= 50){
            smile.setVisibility(View.INVISIBLE);
            skrim.setVisibility(View.VISIBLE);

                player.start();

        }
        else if(event.values[0]>50)
        {if(player.isPlaying()) {
            player.pause();
        }

           smile.setVisibility(View.VISIBLE);
           skrim.setVisibility(View.INVISIBLE);
        }
    }

    }
