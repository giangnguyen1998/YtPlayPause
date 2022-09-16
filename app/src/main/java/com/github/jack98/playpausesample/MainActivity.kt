package com.github.jack98.playpausesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jack98.playpause.PlayPauseImageView

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      val playPauseButton: PlayPauseImageView = findViewById(R.id.play_pause)
      playPauseButton.setOnClickListener { playPauseButton.toggle() }
   }
}