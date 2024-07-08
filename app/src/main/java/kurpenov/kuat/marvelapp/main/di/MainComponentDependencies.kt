package kurpenov.kuat.marvelapp.main.di

import dagger.Module
import dagger.Provides
import kurpenov.kuat.marvelapp.core.di.CommonDependencies
import kurpenov.kuat.marvelapp.core.di.ContentViewSetter

interface MainComponentDependencies : CommonDependencies {
    fun provideContentViewSetter(): ContentViewSetter
}


@Module
interface DrawerModule {

    companion object {
        @Provides
        fun provideContentViewSetter(): ContentViewSetter = ContentViewSetter { activity, view ->
            activity.setContentView(view)
        }
    }
}
