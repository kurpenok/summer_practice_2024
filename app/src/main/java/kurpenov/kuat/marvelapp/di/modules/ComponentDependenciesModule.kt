package kurpenov.kuat.marvelapp.di.modules

import kurpenov.kuat.marvelapp.main.di.MainComponentDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kurpenov.kuat.marvelapp.core.di.ComponentDependencies
import kurpenov.kuat.marvelapp.core.di.ComponentDependenciesKey
import kurpenov.kuat.marvelapp.di.AppComponent
import kurpenov.kuat.marvelapp.screens.personScreen.di.PersonScreenDependencies
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.di.SelectPersonDependencies

@Module
interface ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainComponentDependencies::class)
    fun bindMainComponentDeps(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(PersonScreenDependencies::class)
    fun bindPersonScreenComponentDeps(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectPersonDependencies::class)
    fun bindSelectPersonComponentDeps(appComponent: AppComponent): ComponentDependencies
}
