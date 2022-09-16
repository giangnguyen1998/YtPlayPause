package com.github.jack98.playpause

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

enum class PlayPauseState {
   PLAYING,
   PAUSED
}

/**
 * Custom play pause [AppCompatImageView] with [SimpleAnimationDrawable]
 * state [PlayPauseState]
 */
class PlayPauseImageView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

   private val playPause: SimpleAnimationDrawable = SimpleAnimationDrawable(
      this,
      R.drawable.player_play_pause_transition,
      R.drawable.player_pause
   )
   private val pausePlay: SimpleAnimationDrawable = SimpleAnimationDrawable(
      this,
      R.drawable.player_pause_play_transition,
      R.drawable.player_play
   )

   private var state: PlayPauseState = PlayPauseState.PAUSED

   init {
      setState(state, immediately = true)
   }

   fun toggle(immediately: Boolean = false) {
      when (state) {
         PlayPauseState.PAUSED -> setState(PlayPauseState.PLAYING, immediately)
         PlayPauseState.PLAYING -> setState(PlayPauseState.PAUSED, immediately)
      }
   }

   fun setState(state: PlayPauseState, immediately: Boolean = false) {
      val currentState = this.state
      val isVisible = drawable != null && drawable.isVisible
      val isEqual = currentState == state
      if (!isEqual || !isVisible) {
         when (state) {
            PlayPauseState.PAUSED -> {
               contentDescription = context.getText(R.string.accessibility_play)
               when (immediately) {
                  true -> pausePlay.visible()
                  false -> when (this.state) {
                     PlayPauseState.PLAYING -> pausePlay.invisible()
                     else -> pausePlay.visible()
                  }
               }
            }
            PlayPauseState.PLAYING -> {
               contentDescription = context.getText(R.string.accessibility_pause)
               when (immediately) {
                  true -> playPause.visible()
                  false -> when (this.state) {
                     PlayPauseState.PAUSED -> playPause.invisible()
                     else -> playPause.visible()
                  }
               }
            }
         }
         this.state = state
      }
   }
}