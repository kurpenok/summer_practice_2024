package kurpenov.kuat.marvelapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kurpenov.kuat.marvelapp.BuildConfig
import kurpenov.kuat.marvelapp.core.di.InjectedKey
import kurpenov.kuat.marvelapp.core.di.scope.AppScope
import javax.inject.Named

@Module
interface AppModule {

    companion object {

        @AppScope
        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @JvmStatic
        @Provides
        @Named(InjectedKey.Configuration.VERSION_NAME)
        fun provideAppVersionName(): String = BuildConfig.VERSION_NAME
    }
}
