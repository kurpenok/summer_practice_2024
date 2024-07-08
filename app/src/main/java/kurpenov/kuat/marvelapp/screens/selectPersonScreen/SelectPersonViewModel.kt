package kurpenov.kuat.marvelapp.screens.selectPersonScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kurpenov.kuat.marvelapp.core.repositore.network.Resource
import kurpenov.kuat.marvelapp.network.MarvelNetworkRepository
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.model.HeroCard
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectPersonViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SelectPersonViewModel
    }
    var  repository: MarvelNetworkRepository? = null
        @Inject set

    private val _state = MutableStateFlow(SelectPersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SelectPersonUIEffect>()
    val effect = _effect.asSharedFlow()

    init {
//        Log.e("INIT", "init view model")
//        _state.update {
//            it.copy(
//                listHero = listOf(
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.halk_sample,
//                        title = "Halk",
//                        color = Color.Black,
//                        photoURL = "https://upload.wikimedia.org/wikipedia/ru/c/cc/Hulk_Marvel.jpg",
//                        descriptionHero = "Im halk"
//                    ),
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.iron_man,
//                        title = "Iron man",
//                        color = Purple500,
//                        photoURL = "https://kartinkin.net/uploads/posts/2021-07/1625622278_6-kartinkin-com-p-zheleznii-chelovek-art-art-krasivo-6.jpg",
//                        descriptionHero = " Im iron man"
//                    ),
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.doctor_strange,
//                        title = "Doctor strange",
//                        color = Purple700,
//                        photoURL = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/b/bd/Defender_Strange_Infobox.webp/revision/latest?cb=20220104021959&path-prefix=ru",
//                        descriptionHero = "Im doctor strange"
//                    ),
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.spider_man,
//                        title = "Spider_man",
//                        color = Teal200,
//                        photoURL = "https://upload.wikimedia.org/wikipedia/ru/thumb/c/cb/AmazingSpiderMan50.jpg/231px-AmazingSpiderMan50.jpg",
//                        descriptionHero = "Im spider man"
//                    ),
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.capitain_america,
//                        title = "Captain america",
//                        color = Purple200,
//                        photoURL = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6b/Chris_Evans_as_Steve_Rogers_Captain_America.jpg/640px-Chris_Evans_as_Steve_Rogers_Captain_America.jpg",
//                        descriptionHero = "Im capitan americ"
//                    ),
//                    HeroCard(
//                        id = 0,
//                        image = R.drawable.deadpoll,
//                        title = "Deadpoll",
//                        color = Purple200,
//                        photoURL = "https://www.mirf.ru/wp-content/uploads/2016/02/deadpool_121-e1454924608869.jpg",
//                        descriptionHero = "Im deadpoll"
//                    ),
//                )
//            )
//        }
//        _state.update {
//            it.copy(backgroundColor = state.value.listHero[0].color)
//        }
    }

    fun sendEvent(event: SelectPersonUIEvent) {
        when (event) {
            is SelectPersonUIEvent.OnCurrentIndexChange ->
                viewModelScope.launch {
                    changeCurrentItem(event.newIndex)
                }

            is SelectPersonUIEvent.OnclickHero ->
                viewModelScope.launch {
                    navigateToHeroDetails(event.heroCard)
                }
            SelectPersonUIEvent.OnOpenScreen ->
                viewModelScope.launch { loadData() }
        }
    }

    private fun changeCurrentItem(newIndex: Int) {
        Log.e("TAF", newIndex.toString())
        _state.update {
            it.copy(currentIndex = newIndex)
        }
        _state.update {
            it.copy(backgroundColor = state.value.listHero[newIndex].color)
        }
        Log.e("COLOR", _state.value.backgroundColor.toString())
    }

    private suspend fun navigateToHeroDetails(heroCard: HeroCard) {
        _effect.emit(SelectPersonUIEffect.NavigateToPersonScreen(heroCard.id))
    }

    private suspend fun loadData() {
        val hero : Resource<List<HeroCard>> = repository?.getAllHeroes()!!
        when (hero) {
            is Resource.Success ->
                updateView(hero.data!!)
            is Resource.Error ->
                showError(hero.message!!)
        }
    }

    private suspend fun updateView(heroCards: List<HeroCard>) {
        _state.update {
            it.copy(
                listHero = heroCards,
                backgroundColor = heroCards[0].color,
                currentIndex = 0,
                getDataIsSuccessful = true
            )
        }
    }

    private fun showError(error: String) {
        _state.update {
            it.copy(
                getDataIsSuccessful = false,
                errorToLoadData = error
            )
        }
    }
}
