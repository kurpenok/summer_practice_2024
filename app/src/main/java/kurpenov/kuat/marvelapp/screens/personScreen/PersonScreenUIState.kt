package kurpenov.kuat.marvelapp.screens.personScreen

data class PersonUIState (
    val url: String = "",
    val personName: String = "",
    val description: String = ""
){
    companion object {
        val Empty = PersonUIState()
    }
}

sealed interface PersonScreenUIEvent {
    object OnBackClick : PersonScreenUIEvent
    data class OnGetData (val characterId: Int) : PersonScreenUIEvent
    object OnOpenWithArg : PersonScreenUIEvent
}

sealed interface PersonScreenUIEffect {
    object NavigateBack : PersonScreenUIEffect
    data class ErrorToLoadData(val error: String) : PersonScreenUIEffect
}
