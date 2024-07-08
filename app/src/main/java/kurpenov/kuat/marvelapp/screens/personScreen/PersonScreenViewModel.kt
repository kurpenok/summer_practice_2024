package kurpenov.kuat.marvelapp.screens.personScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kurpenov.kuat.marvelapp.core.model.HeroInfo
import kurpenov.kuat.marvelapp.core.repositore.network.Resource
import kurpenov.kuat.marvelapp.network.MarvelNetworkRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonScreenViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): PersonScreenViewModel
    }
    var repository: MarvelNetworkRepository? = null
        @Inject set

    private val _state = MutableStateFlow(PersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PersonScreenUIEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: PersonScreenUIEvent) {
        when(event) {
            is PersonScreenUIEvent.OnGetData ->
                viewModelScope.launch {
                    getDataAfterNav(event.characterId)
                }

            PersonScreenUIEvent.OnOpenWithArg -> TODO()
        }
    }

    private suspend fun updateView(heroInfo: HeroInfo) {
        _state.update {
            it.copy(
                description = heroInfo.descriptionHero,
                personName = heroInfo.heroName,
                url = heroInfo.photoUrl
            )
        }
    }

    private suspend fun getDataAfterNav(id: Int) {
        val hero : Resource<HeroInfo> = repository?.getHeroInfo(id)!!
        when (hero) {
            is Resource.Success ->
                updateView(hero.data!!)
            is Resource.Error ->
                _effect.emit(PersonScreenUIEffect.ErrorToLoadData(hero.message!!))
        }
    }
}


