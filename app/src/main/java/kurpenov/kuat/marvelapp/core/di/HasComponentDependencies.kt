package kurpenov.kuat.marvelapp.core.di

import kurpenov.kuat.marvelapp.core.di.ComponentDependenciesProvider

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}
