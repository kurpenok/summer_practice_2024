package kurpenov.kuat.marvelapp.screens.selectPersonScreen.model

import androidx.compose.ui.graphics.Color

data class HeroCard(
    val id: Int, //вместо int сделать другой индификатор для api
    val title: String,
    val color: Color,
    val descriptionHero: String,
    val photoURL: String
)
