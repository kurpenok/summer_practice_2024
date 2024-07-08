package kurpenov.kuat.marvelapp

import android.app.Application
import kurpenov.kuat.marvelapp.core.di.ComponentDependenciesProvider
import kurpenov.kuat.marvelapp.core.di.HasComponentDependencies
import kurpenov.kuat.marvelapp.di.DaggerAppComponent
import javax.inject.Inject

open class HeadlinesComposeApp : Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)
    }

}
