package kurpenov.kuat.marvelapp.di

import android.app.Application
import kurpenov.kuat.marvelapp.di.modules.ComponentDependenciesModule
import kurpenov.kuat.marvelapp.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import kurpenov.kuat.marvelapp.HeadlinesComposeApp
import kurpenov.kuat.marvelapp.MainActivity
import kurpenov.kuat.marvelapp.core.di.CommonDependencies
import kurpenov.kuat.marvelapp.core.di.scope.AppScope
import kurpenov.kuat.marvelapp.main.di.DrawerModule
import kurpenov.kuat.marvelapp.main.di.MainComponentDependencies
import kurpenov.kuat.marvelapp.screens.personScreen.di.PersonScreenDependencies
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.di.SelectPersonDependencies

@AppScope
@Component(
    modules = [
        AppModule::class,
        ComponentDependenciesModule::class,
        DrawerModule::class,
    ]
)
interface AppComponent :
    CommonDependencies,
    MainComponentDependencies,
    PersonScreenDependencies,
    SelectPersonDependencies
{

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(app: HeadlinesComposeApp)
    fun inject(mainActivity: MainActivity)
}
