package com.udemy.playingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    boolean isPlay = true;
    SeekBar volumeSeekBar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

         mediaPlayer = MediaPlayer.create(this, R.raw.thunderstruck);

         volumeSeekBar = findViewById(R.id.volumeSeekBar);
         volumeSeekBar.setMax(maxVolume);
         volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 Log.d("Progress","" + i);
                 audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i , 0);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
    }

    public void play(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void replay(View view) {
        if (isPlay){
            Button replayButton = (Button)findViewById(R.id.replayButton);
            replayButton.setText("play music");
            mediaPlayer.start();
            isPlay = false;
        } else {
            Button replayButton = (Button)findViewById(R.id.replayButton);
            replayButton.setText("pause music");
            mediaPlayer.pause();
            isPlay = true;
        }
    }
}