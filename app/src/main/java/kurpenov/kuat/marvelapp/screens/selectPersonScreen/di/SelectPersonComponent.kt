package kurpenov.kuat.marvelapp.screens.selectPersonScreen.di

import android.app.Activity
import kurpenov.kuat.marvelapp.core.di.featureComponent
import kurpenov.kuat.marvelapp.core.di.findComponentDependencies
import kurpenov.kuat.marvelapp.core.di.modules.ViewModelModule
import kurpenov.kuat.marvelapp.core.di.scope.FeatureScope
import dagger.Component
import kurpenov.kuat.marvelapp.network.di.RetrofitModule
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.SelectPersonViewModel
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.di.module.SelectPersonViewModelModule

internal val selectPersonComponent = featureComponent<SelectPersonComponent, Activity> { activity ->
    DaggerSelectPersonComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, SelectPersonViewModelModule::class, RetrofitModule::class],
    dependencies = [SelectPersonDependencies::class]
)
internal interface SelectPersonComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: SelectPersonDependencies): SelectPersonComponent
    }

    val selectPersonViewModelFactory: SelectPersonViewModel.Factory
}
