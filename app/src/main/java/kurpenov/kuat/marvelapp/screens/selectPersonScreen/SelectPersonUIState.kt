package kurpenov.kuat.marvelapp.screens.selectPersonScreen

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.model.HeroCard
import kurpenov.kuat.marvelapp.ui.theme.Dune

@Immutable
data class SelectPersonUIState(
    val backgroundColor: Color = Dune,
    val listHero: List<HeroCard> = listOf(),
    val currentIndex: Int = 0,
    val getDataIsSuccessful: Boolean = false,
    val errorToLoadData: String = ""
) {
    companion object {
        val Empty = SelectPersonUIState()
    }
}

@Immutable
sealed interface SelectPersonUIEvent {
    data class OnCurrentIndexChange(val newIndex: Int): SelectPersonUIEvent
    data class OnclickHero(val heroCard: HeroCard): SelectPersonUIEvent
    object OnOpenScreen : SelectPersonUIEvent
}

@Immutable
sealed interface SelectPersonUIEffect {
    data class NavigateToPersonScreen(val characterId: Int) : SelectPersonUIEffect
    data class ErrorToLoadData(val error: String) : SelectPersonUIEffect
}

