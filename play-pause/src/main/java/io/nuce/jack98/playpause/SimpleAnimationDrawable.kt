package io.nuce.jack98.playpause

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Simple custom [AnimationDrawable] for play pause player
 */
class SimpleAnimationDrawable(
   private val imageView: ImageView,
   @DrawableRes private val transitionRes: Int,
   @DrawableRes private val drawableRes: Int
) : AnimationDrawable() {

   private val drawable: Drawable?
   private var visible: Boolean = false

   init {
      val transitionDrawable = ContextCompat.getDrawable(imageView.context, transitionRes)
      drawable = ContextCompat.getDrawable(imageView.context, drawableRes)
      when (transitionDrawable) {
         is AnimationDrawable -> {
            isOneShot = transitionDrawable.isOneShot
            for (i in 0 until transitionDrawable.numberOfFrames) {
               addFrame(transitionDrawable.getFrame(i), transitionDrawable.getDuration(i))
            }
         }
         else -> addFrame(drawable!!, 0)
      }
   }

   override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
      val isVisible = super.setVisible(visible, restart)
      if (!isVisible && !this.visible) {
         drawable?.setVisible(false, false)
      }
      return isVisible
   }

   fun invisible() {
      visible = false
      imageView.setImageDrawable(this)
      stop()
      selectDrawable(0)
      start()
   }

   fun visible() {
      visible = true
      imageView.setImageDrawable(drawable)
      stop()
   }
}