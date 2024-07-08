package kurpenov.kuat.marvelapp.screens.personScreen.di

import android.app.Activity
import kurpenov.kuat.marvelapp.core.di.featureComponent
import kurpenov.kuat.marvelapp.core.di.modules.ViewModelModule
import kurpenov.kuat.marvelapp.core.di.scope.FeatureScope
import dagger.Component
import kurpenov.kuat.marvelapp.core.di.findComponentDependencies
import kurpenov.kuat.marvelapp.network.di.RetrofitModule
import kurpenov.kuat.marvelapp.screens.personScreen.PersonScreenViewModel
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.di.module.SelectPersonViewModelModule

internal val personScreenComponent = featureComponent<PersonScreenComponent, Activity> { activity ->
    DaggerPersonScreenComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, SelectPersonViewModelModule::class, RetrofitModule::class],
    dependencies = [PersonScreenDependencies::class]
)
internal interface PersonScreenComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: PersonScreenDependencies): PersonScreenComponent
    }

    val personScreenViewModelFactory: PersonScreenViewModel.Factory
}
