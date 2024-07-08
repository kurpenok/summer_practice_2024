package kurpenov.kuat.marvelapp.core.di

import android.app.Activity
import android.view.View
import dagger.Provides

fun interface ContentViewSetter {
    fun setContentView(activity: Activity, view: View)
}
