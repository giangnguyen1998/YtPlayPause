# PlayPauseImageView

A simple library, created to handle play/pause behavior like youtube (transition animation)

# Download

The Gradle dependency is available
via [https://central.sonatype.org/pages/ossrh-guide.html][mavenCentral]. To be able to load this
library, you have to add the repository to your project's gradle file:

```gradle
allprojects {
  repositories {
    ...
    mavenCentral()
  }
}
```

Then, in your app's directory, you can include it the same way like other libraries:

```gradle
android {
  ...
  // If you face problems during building you should try including the below lines if you
  // haven't already
  
  // compileOptions {
  //   sourceCompatibility JavaVersion.VERSION_1_8
  //   targetCompatibility JavaVersion.VERSION_1_8
  // }
}

dependencies {
  implementation 'io.nuce.jack98:play-pause:1.0.0'
}
```

The minimum API level supported by this library is API level 21 (Lollipop). So feedback is welcomed.

# Getting started

In order to start using the YouTube overlay, the easiest way is to include it directly into your XML
layout, e.g. on top of `PlayPauseImageView` or inside ExoPlayer's controller:

```xml

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="match_parent" android:layout_height="match_parent"
   android:background="@color/black">

   <io.nuce.jack98.playpause.PlayPauseImageView android:id="@+id/play_pause"
      android:layout_width="wrap_content" android:layout_height="wrap_content"
      android:layout_gravity="center" />

</FrameLayout>
```

Then, inside your `Activity` or `Fragment`, you can specify which preparations should be done before
and after the animation, but at least, you have got to toggle the visibility when click play/pause
button, force property that mean skip the animation or not.

```kotlin
playPauseButton.setOnClickListener { playPauseButton.toggle() }
playPauseButton.setState(PlayPauseState.PLAYING, force = false)
playPauseButton.setState(PlayPauseState.PAUSED, force = true)
```

This way, you have more control about the appearance, for example you could apply a fading animation
to it. For a full initialization you can refer to the demo application's MainActivity and layout
files.

---

# API documentation

The following sections provide detailed documentation for the components of the library.